package com.dl.controller;

import com.dl.pojo.RoleInfo;
import com.dl.pojo.UserInfo;
import com.dl.pojo.UserInfoParam;
import com.dl.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName UserInfoController
 * @description
 * @author:duanli
 * @createDate:2020.10.14 13:49
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //注入上传文件的绝对物理目录路径
    @Value("${file.address}")
    private String fileAddres;

    //注入访问上传文件的静态资源路径
    @Value("${file.staticPath}")
    private String fileStaicPath;

    //访问用户管理的列表页面
    @RequestMapping("/list")
    public String findList(Model model){
        List<UserInfo> userInfos = userInfoService.findAll();
        //存入到request里
        model.addAttribute("list",userInfos);
        return "/userInfo/list";
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public boolean updateStatus(@RequestParam("id") Integer id){
        return userInfoService.update(id);
    }


    //访问用户新增页面
    @RequestMapping("/add")
    public String add(HashMap map){
        List<RoleInfo> list= userInfoService.selectRole();
        map.put("roleList",list);
        return "/userInfo/add";
    }

    //图片上传
    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file, HttpServletRequest request){
        //记录图片后缀
        String prefix="";
        //记录当天日期
        String dateStr="";
        try{
            if(file!=null){
                //获取上传图片的后缀
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);

                //生成随机一个uuid字符串作为上传图片的名称，保持图片名称的唯一性
                String uuid = UUID.randomUUID()+"";
                //获取当天日期作为上传图片的上一级目录
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //得到上传图片的全路径
                String filepath = fileAddres+"\\" + dateStr+"\\"+uuid+"." + prefix;
                //打印查看上传路径
                System.out.println(filepath);

                File files=new File(filepath);
                //判读目录是否存在，不存在就创建
                if(!files.isDirectory()){
                    files.mkdirs();
                }
                //上传图片
                file.transferTo(files);
                //记录图片在项目中的路径，状态码，并传到前端
                Map<String,Object> map2=new HashMap<>();
                map2.put("src",fileStaicPath+"/"+ dateStr+"/"+uuid+"." + prefix);
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);

                return map;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }

    @RequestMapping("/save/{path}")
    @ResponseBody
    public boolean add(UserInfoParam userInfoParam, @PathVariable("path") String path){
        if (path.equals("add")){
            //新增方法
            return userInfoService.add(userInfoParam);
        }else {
            //修改方法
        }
        return false;

    }
    //访问用户修改页面
    @RequestMapping("/update")
    public String update(Model model,@RequestParam("id") Integer id){
        //查询要修改用户的信息
        UserInfo userInfo = userInfoService.selectById(id);
        model.addAttribute("userInfo",userInfo);
        return "userInfo/update";
    }

}
