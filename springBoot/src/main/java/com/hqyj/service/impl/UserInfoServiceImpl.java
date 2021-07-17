package com.hqyj.service.impl;

import com.hqyj.dao.RoleInfoDao;
import com.hqyj.dao.UserInfoDao;
import com.hqyj.pojo.RoleInfo;
import com.hqyj.pojo.UserDetail;
import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.UserInfoParam;
import com.hqyj.service.UserInfoService;
import com.hqyj.util.MdFive;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    //注入dao
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RoleInfoDao roleInfoDao;
    @Autowired
    private MdFive mdFive;


    //注入redis
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //注入邮件发送类
    @Autowired
    JavaMailSender javaMailSender;

    //注入发送者邮箱
    @Value("${spring.mail.username}")
    private String sendEmail;



    @Override
    public HashMap<String, Object> login(UserInfo userInfo, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        UserInfo user = userInfoDao.findByUserName(userInfo.getUserName());
        //判断用户名输入是否正确
        if (user != null) {
            //判读密码输入是否正确
            if (user.getUserPwd().equals(userInfo.getUserPwd())) {

                if (user.getUserDetail().getStatus() == 1) {
                    //创建session对象
                    HttpSession session = request.getSession();
                    //存入session
                    session.setAttribute("userInfo", user);
                    map.put("info", true);
                } else {
                    map.put("info", "用户已经被禁用,请联系管理员");
                }

            } else {
                map.put("info", "密码输入不正确");
            }

        } else {
            map.put("info", "用户名输入不正确");
        }
        return map;
    }

    //记录失败次数
    private int setFailCount(String userName){
        //定义键名
        String key = "user:"+userName+"failcount";
        //获取失败次数
        int num = this.getFailCount(userName);
        if (num<0){//第一次输入密码错误
            redisTemplate.opsForValue().set(key,1,1, TimeUnit.MINUTES);

        }else {
            //自增
            redisTemplate.opsForValue().increment(key,new Double(1));

        }
        return num;
    }

    //获取失败次数
    public int getFailCount(String userName){
        //定义键名
        String key = "user:"+userName+"failcount";
        //获取失败次数
        Object num = redisTemplate.opsForValue().get(key);
        if (num!=null){
            return (int)num;
        }else {
            return -1;
        }

    }

    //判断用户是否被锁定
    private  int getLockTime(String userName){
        //定义键名
        String key = "user:"+userName+"lockTime";
        //获取失败失效时间
        long time = redisTemplate.getExpire(key,TimeUnit.MINUTES);
        if (time>0){
            return (int)time;
        }else {
            return -1;
        }

    }

    @Override
    public Page<UserInfo> select(UserInfoParam userInfoParam) {
        //创建分页参数对象
        Pageable pageable = PageRequest.of(userInfoParam.getPage() - 1, userInfoParam.getRow());

        if (userInfoParam.getUserName() != null && !userInfoParam.getUserName().equals("")) {
            if (userInfoParam.getEmail() != null && !userInfoParam.getEmail().equals("")) {
                return userInfoDao.findByUserNameAndUserDetailEmail(userInfoParam.getUserName(), userInfoParam.getEmail(), pageable);
            } else {
                return userInfoDao.findByUserName(userInfoParam.getUserName(), pageable);
            }
        } else if (userInfoParam.getEmail() != null && !userInfoParam.getEmail().equals("")) {
            return userInfoDao.findByUserDetailEmailLike("%" + userInfoParam.getEmail(), pageable);
        } else {
            return userInfoDao.findAll(pageable);
        }

    }

    @Override
    public boolean update(Integer uId) {
        try {
            UserInfo user = userInfoDao.findById(uId).get();
            if (user != null) {
                Integer status = user.getUserDetail().getStatus();
                if (status == 0) {
                    user.getUserDetail().setStatus(1);
                } else {
                    user.getUserDetail().setStatus(0);
                }
                userInfoDao.save(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RoleInfo> selectRole() {
        return roleInfoDao.findAll();
    }

    @Override
    public boolean add(UserInfoParam userInfoParam) {
        try {
            //用户表的
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(userInfoParam.getUserName());
            userInfo.setUserPwd(mdFive.encrypt(userInfoParam.getUserPwd(),userInfo.getUserName()));

            //用户详细表的信息
            UserDetail userDetail = new UserDetail();
            userDetail.setTel(userInfoParam.getTel());
            userDetail.setEmail(userInfoParam.getEmail());
            userDetail.setJoinTime(new Date());
            userDetail.setStatus(0);
            userDetail.setUrl(userInfoParam.getUrl());

            List<RoleInfo> roleInfoList = new ArrayList<>();
            //角色 表的信息
            if (!userInfoParam.getRoleId().equals("")) {
                //取出角色id
                String rrr[] = userInfoParam.getRoleId().split(",");
                for (int i = 0; i < rrr.length; i++) {
                    RoleInfo r = roleInfoDao.findById(Integer.parseInt(rrr[i])).get();
                    roleInfoList.add(r);
                }
            }
            //添加角色
            userInfo.setRoleInfoList(roleInfoList);

            userInfo.setUserDetail(userDetail);
            userInfoDao.save(userInfo);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UserInfoParam userInfoParam) {
        try {
            //用户表的
            UserInfo userInfo = userInfoDao.findById(userInfoParam.getuId()).get();
            userInfo.setUserName(userInfoParam.getUserName());


            //用户详细表的信息
            UserDetail userDetail = userInfo.getUserDetail();
            userDetail.setTel(userInfoParam.getTel());
            userDetail.setEmail(userInfoParam.getEmail());
            userDetail.setJoinTime(new Date());
            userDetail.setStatus(0);
            userDetail.setUrl(userInfoParam.getUrl());


            //删除用户的角色
            roleInfoDao.deleteUserRolet(userInfoParam.getuId());

            List<RoleInfo> roleInfoList = new ArrayList<>();
            //角色 表的信息
            if (!userInfoParam.getRoleId().equals("")) {
                //取出角色id
                String rrr[] = userInfoParam.getRoleId().split(",");
                for (int i = 0; i < rrr.length; i++) {
                    RoleInfo r = roleInfoDao.findById(Integer.parseInt(rrr[i])).get();
                    roleInfoList.add(r);
                }
            }
            //添加角色
            userInfo.setRoleInfoList(roleInfoList);

            userInfo.setUserDetail(userDetail);
            userInfoDao.save(userInfo);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserInfo selectByUId(Integer uId) {
        return userInfoDao.findById(uId).get();
    }

    @Override
    public UserInfo selectByName(String name) {
        return userInfoDao.findByUserName(name);
    }

    @Override
    public boolean del(Integer uId) {
        try {
            roleInfoDao.deleteUserRolet(uId);
            userInfoDao.deleteById(uId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delAll(String uIds, String udIds) {
        try {
            if (uIds != null && !uIds.equals("")) {
                String[] arrs = uIds.split(",");
                String[] arrs1 = udIds.split(",");
                roleInfoDao.deleteUserRoleAll(Arrays.asList(arrs));
                userInfoDao.deleteUserAll(Arrays.asList(arrs));
                userInfoDao.deleteUserTailAll(Arrays.asList(arrs1));
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //excel导出
    @Override
    public void excelWrite(HttpServletResponse response) {
        OutputStream output = null;
        try {
            //创建 操作excel表的java对象
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建sheet，并且命名为用户表
            HSSFSheet sheet = wb.createSheet("用户表");

            // 创建HSSFRow对象，先写入列名
            HSSFRow colName = sheet.createRow(0);
            // 写入入第一行列名
            colName.createCell(0).setCellValue("编号");
            colName.createCell(1).setCellValue("登录名");
            colName.createCell(2).setCellValue("手机");
            colName.createCell(3).setCellValue("邮箱");
            colName.createCell(4).setCellValue("角色");
            colName.createCell(5).setCellValue("加入时间");
            colName.createCell(6).setCellValue("状态");
            colName.createCell(7).setCellValue("密码");
            colName.createCell(8).setCellValue("头像地址");
            //查询员工所有信息
            //创建分页参数对象
            Pageable pageable = PageRequest.of(0, 10);
            List<UserInfo> list = userInfoDao.findAll(pageable).getContent();

            for (int i = 1; i <= list.size(); i++) {
                //从第二行开始写入数据
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(list.get(i - 1).getuId());
                row.createCell(1).setCellValue(list.get(i - 1).getUserName());
                row.createCell(2).setCellValue(list.get(i - 1).getUserDetail().getTel());
                row.createCell(3).setCellValue(list.get(i - 1).getUserDetail().getEmail());
                String roleName = "";
                if (list.get(i - 1).getRoleInfoList() != null) {
                    for (RoleInfo r : list.get(i - 1).getRoleInfoList()) {
                        roleName += r.getrName() + "";
                    }
                }
                row.createCell(4).setCellValue(roleName);
                row.createCell(5).setCellValue(list.get(i - 1).getUserDetail().getJoinTime());
                row.createCell(6).setCellValue(list.get(i - 1).getUserDetail().getStatus());
                row.createCell(7).setCellValue(list.get(i - 1).getUserPwd());
                row.createCell(8).setCellValue(list.get(i - 1).getUserDetail().getUrl());

            }


            //输出Excel文件到页面
            output = response.getOutputStream();
            String fileName = "用户表";
            //解决中文文件名下载 变成下划线的问题
            fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1") + "";

            response.reset();
            //设置响应表头
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            //文件格式
            response.setContentType("application/msexcel");
            //文件下载
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public boolean sendEmail(String email, HttpServletRequest request) {

        try {
            //创建邮件对象
            SimpleMailMessage mail = new SimpleMailMessage();
            //邮件标题
            mail.setSubject("xxx公司的验证码");
            //收件人
            mail.setTo(email);
            //发件人
            mail.setFrom(sendEmail);
            //邮件正文
            Random rd = new Random();
            int num =  rd.nextInt(9000)+1000;
            mail.setText(num + "");
            //把验证
            request.getSession().setAttribute("valCode", num);
            //发送邮件
            javaMailSender.send(mail);
            System.out.println("邮件发送成功");
            return true;
        } catch (MailException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<String, Object> loginShiro(UserInfo userInfo, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        UserInfo user = userInfoDao.findByUserName(userInfo.getUserName());
        //判断用户名输入是否正确
        if (user != null) {
            if (user.getUserDetail().getStatus() == 1) {
               try {
                   if (getLockTime(user.getUserName())>0){
                       map.put("info","账号被锁定，请1分钟后再尝试");
                   }else {
                       //创建令牌
                       UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getUserPwd());
                       //获取shiro的用户对象
                       Subject subject = SecurityUtils.getSubject();
                       //shiro登录
                       subject.login(token);
                       //创建session对象
                       HttpSession session = request.getSession();
                       //存入session
                       session.setAttribute("userInfo", user);
                       map.put("info", true);
                   }

               }catch (IncorrectCredentialsException e){
                  //记录输入密码错误次数
                   setFailCount(user.getUserName());
                   //获取输入次数
                   int num = getFailCount(user.getUserName());
                   if (num >= 3){
                       map.put("info","账号被锁定，请1分钟后再尝试");

                   }else {
                       map.put("info","密码输入错误，你还有"+(3-num)+"次机会");
                   }
               }

            } else {
                map.put("info", "用户已经被禁用,请联系管理员");
            }

        } /*else {
            map.put("info", "密码输入不正确");
        }*/

        return map;
    }
}


