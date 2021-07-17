package com.dl.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 验证码图片的生成
 * @author:duanliIOEx
 * @createDate:2020/9/3 14:13
 */
@WebServlet("/gc")
public class GetCapact extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CircleCaptcha captcha= CaptchaUtil.createCircleCaptcha(120,40,4,10);
        ServletOutputStream sos = resp.getOutputStream();
        String rightCode=captcha.getCode();
        req.getSession().setAttribute("rightCode",rightCode);
        captcha.write(sos);
        sos.flush();
        sos.close();
    }
}
