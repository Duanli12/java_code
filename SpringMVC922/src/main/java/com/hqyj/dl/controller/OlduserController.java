package com.hqyj.dl.controller;

import com.hqyj.dl.pojo.User;
import com.hqyj.dl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName userController
 * @description
 * @author:duanli
 * @createDate:2020/9/21 19:04
 */
@Controller
@RequestMapping("/users")//放在类上面表示上层的URL，放在类下面表示下一层的URL，最终URL为两个拼一起
public class OlduserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)//放在类上面表示上层的URL，放在类下面表示下一层的URL，最终URL为两个拼一起
    public String registerUser(User user, ModelMap map) {
        //1.接受请求参数，处理请求参数
        //2.调用业务，拿到数据
        System.out.println(user);
        //给注册结果页面传了一个name的值：用户名
        map.addAttribute("name", user.getName());
        //3.跳转jsp页面
        /*return "/WEB-INF/jsp/userRegisterResult.jsp";*/
        return "userRegisterResult";

    }

    @RequestMapping("/modify")
    public String modifyUser(User user, Model map) {
        return null;
    }

    @GetMapping("/getRegister")
    public String getRegister() {

        return "user";
    }

    @PostMapping("/ajaxregister")
    @ResponseBody
    public Map<String, Object> ajaxRegister(User user) {
        System.out.println(user);
        Map<String, Object> result = new HashMap<>();
        boolean added = userService.addOne(user);
        if (added) {
            result.put("code", 0);
            result.put("user", user);
            result.put("message", "注册成功");

        } else {
            result.put("code", -1);
            result.put("message", "注册失败！");
        }
        return result;


    }

    @PostMapping("ajaxregisterfile")
    @ResponseBody
    public Map<String, Object> ajaxRegisterFile(User user, MultipartFile headImage, MultipartHttpServletRequest request) {
        System.out.println(user);
        if (headImage != null) {
            saveFile(headImage, request);
            System.out.println(headImage.getOriginalFilename());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("user", user);
        result.put("message", "注册成功");
        return result;
    }

    private void saveFile(MultipartFile headImage, MultipartHttpServletRequest request) {
        if (headImage.getContentType().startsWith("image")) {
            //将要保存的路径，这个是web上面的路径
            String webPath = "/static/upload/";
            //将web的虚拟路径转成真是路径
            String realPath = request.getServletContext().getRealPath(webPath);
            //new File(realPath).mkdir();
            //拿文件名
            String fileName = headImage.getOriginalFilename();
            //完整的文件名
            String fileToPath = realPath + fileName;

            try {
                //保存这个文件
                headImage.transferTo(new File(fileToPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
