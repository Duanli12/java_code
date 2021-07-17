package com.fs.dao;

import com.fs.model.Student;

public interface StuDao {
    Student queryUserByUserNameAndPassword(String username, String password);

    void register(String username, String password);



}
