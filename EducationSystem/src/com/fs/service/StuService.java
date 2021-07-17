package com.fs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StuService {

    String login(String username, String password, String code, HttpServletRequest req, HttpServletResponse resp);

    String register(String username, String password, HttpServletRequest req, HttpServletResponse resp);
}
