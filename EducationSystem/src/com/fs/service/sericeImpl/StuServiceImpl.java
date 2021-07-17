package com.fs.service.sericeImpl;

import com.fs.dao.StuDao;
import com.fs.dao.daoImpl.StuDaoImpl;
import com.fs.model.Student;
import com.fs.service.StuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/10 16:36
 */
public class StuServiceImpl implements StuService {
    StuDao sd = new StuDaoImpl();

    @Override
    public String login(String username, String password, String code, HttpServletRequest req, HttpServletResponse resp) {
        String rightCode = (String)req.getSession().getAttribute("rightCode");
        if (rightCode.equals(code)){
            Student st = sd.queryUserByUserNameAndPassword(username,password);
            if(st!=null){
                req.getSession().setAttribute("USER",st);
                return "SUCCESS";
            }else {
                return "ERROR";
            }
        }else {
            return "ERRORCODE";
        }
    }

    @Override
    public String register(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
        Student st = sd.queryUserByUserNameAndPassword(username,password);
        if(st!=null){
            return "ERROR";

        }else {
           sd.register(username,password);
            return "SUCCESS";

        }
    }


}
