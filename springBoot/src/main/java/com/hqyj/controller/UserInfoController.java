package com.hqyj.controller;

import com.hqyj.pojo.RoleInfo;
import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.UserInfoParam;
import com.hqyj.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    //图片服务器路径
    @Value("${file.address}")
    private String serviceUrl;

    //图片访问路径
    @Value("${file.staticPath}")
    private String FilePath;

    //访问会员列表页面
    @RequestMapping("/userlist")
    public String userlist(){
        return "/userlist";
    }

    //访问删除会员页面
    @RequestMapping("/userdel")
    public String userdel(){
        return "/userdel";
    }

    //访问管理会员页面
    @RequestMapping("/usermag")
    public String usermag(){
        return "/usermag";
    }


    //访问用户管理的列表页面
    @RequestMapping("/list")
    public String list(Model model,UserInfoParam userInfoParam){
        //当前页的集合
        Page<UserInfo> userInfoPage = userInfoService.select(userInfoParam);

        //存入request里当前页的集合
        model.addAttribute("list",userInfoPage.getContent());
        //存入总页数
        model.addAttribute("totalPages",userInfoPage.getTotalPages());
         //存入总条数
        model.addAttribute("totalElments",userInfoPage.getTotalElements());
        if(userInfoPage.getNumber()<=0){
            //上一页
            model.addAttribute("pre",1);
        }else{
            //上一页
            model.addAttribute("pre",userInfoPage.getNumber());
        }
        if((userInfoPage.getNumber()+2)>=userInfoPage.getTotalPages()){
            //下一页
            model.addAttribute("next",userInfoPage.getTotalPages());
        }else{
            //下一页
            model.addAttribute("next",userInfoPage.getNumber()+2);
        }

        //当前页
        model.addAttribute("cur",userInfoPage.getNumber()+1);
        //尾页
        model.addAttribute("last",userInfoPage.getTotalPages());

        return "/userInfo/list";
    }


    //访问用户管理的新增页面
    @RequestMapping("/add")
    public String add(HashMap map){
      List<RoleInfo> list=  userInfoService.selectRole();
      map.put("roleList",list);
        return "/userInfo/add";
    }
    //访问用户管理的修改面
    @RequestMapping("/update")
    public String update(HashMap map,@RequestParam("uId") Integer uId){
        List<RoleInfo> list=  userInfoService.selectRole();
        map.put("roleList",list);
        // 查询要修改的用户
        UserInfo userInfo = userInfoService.selectByUId(uId);
        map.put("userInfo",userInfo);
        return "/userInfo/update";
    }
    //加载用户的角色信息
    @RequestMapping("/loadMyRole")
    @ResponseBody
    public HashMap<String,Object> loadMyRole(@RequestParam("uId") Integer uId){
        HashMap<String,Object> map = new HashMap<String,Object>();
        List<RoleInfo> list=  userInfoService.selectRole();
        map.put("roleInfoList",list);
        // 查询要修改的用户
        UserInfo userInfo = userInfoService.selectByUId(uId);
        map.put("myRoleList",userInfo.getRoleInfoList());
        return map;
    }
    @RequestMapping("/updateStatus")
    @ResponseBody
    public boolean updateStatus(@RequestParam("uId") Integer uId){
        return userInfoService.update(uId);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public HashMap<String,Object> upload(MultipartFile file)  {
        HashMap<String,Object> map = new HashMap<String,Object>();

        try {
            //获取文件的后缀
            String fileName = file.getOriginalFilename();
            String oName = fileName.substring(fileName.lastIndexOf(".")+1);

            //时间目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String timeDf = sdf.format(new Date());

            //保证上传文件的唯一性，文件名
            String fName = UUID.randomUUID()+"";

            //拼接上传文件的全路径
            String uploadName = serviceUrl+"\\"+timeDf+"\\"+fName+"."+oName;
            //创建文件对象
            File f = new File(uploadName);
            //判读目录是否存在，不存在就创建
            if(!f.isDirectory()){
                f.mkdirs();
            }

            //保存文件
            file.transferTo(f);

            //把图片路径传到前端
            map.put("src",FilePath+"/"+timeDf+"/"+fName+"."+oName);
            //成功
            map.put("code",0);

            return map;
        } catch (IOException e) {

            e.printStackTrace();
        }
        //失败提示
        map.put("src","");
        map.put("code",1);
        return map;
    }

    //PathVariable 可选路径
    @RequestMapping("/save/{path}")
    @ResponseBody
    public boolean add(UserInfoParam userInfoParam, @PathVariable("path") String path){
        if(path.equals("add")){
            //新增方法
            return userInfoService.add(userInfoParam);
        }else{
            //修改方法
            return userInfoService.update(userInfoParam);
        }

    }

    @RequestMapping("/del")
    @ResponseBody
    public boolean del(Integer uId){

        return userInfoService.del(uId);


    }
    @RequestMapping("/delAll")
    @ResponseBody
    public boolean delAll(String uIds,String udIds){
        return userInfoService.delAll(uIds,udIds);

    }

    @RequestMapping("/excelWrite")
    public void excelWrite(HttpServletResponse response){
        //excel文件导出
        userInfoService.excelWrite(response);
    }

    //发送验证码
    @RequestMapping("/sendCode")
    @ResponseBody
    public boolean sendCode(String email, HttpServletRequest request){
        return userInfoService.sendEmail(email,request);

    }

    //确认
    @RequestMapping("/getValCode")
    @ResponseBody
    public boolean getValCode(String valCode, HttpServletRequest request){

        if(valCode.equals(request.getSession().getAttribute("valCode")+"")){
            return true;
        }
        return false;

    }

    //访问修改密码的页面
    @RequestMapping("/updatePwd")
    public String updatePwd(){

        return "/userInfo/updatePwd";
    }





}
