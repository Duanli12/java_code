package com.dl.dao;

import com.dl.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @ClassName RoleDao
 * @description
 * @author:duanli
 * @createDate:2020/9/15 21:40
 */
public interface RoleDao {
    Set<Role> queryAll();
    int insertByRole(Role role);
    int deleteByRole(Role role);
    int updateByRole(Role role);
    int deleteByRoleId(int roleId);
    int deleteByRoleIds(@Param("roleIds") Set<Integer> roleIds);
}
