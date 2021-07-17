package com.hqyj.dao;

import com.hqyj.pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}