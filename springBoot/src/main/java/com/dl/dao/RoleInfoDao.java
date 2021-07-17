package com.dl.dao;

import com.dl.pojo.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @ClassName RoleInfoDao
 * @description
 * @author:duanli
 * @createDate:2020.10.15 16:40
 */
@Repository
public interface RoleInfoDao extends JpaRepository<RoleInfo,Integer> {
}
