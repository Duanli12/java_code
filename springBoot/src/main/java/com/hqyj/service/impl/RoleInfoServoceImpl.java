package com.hqyj.service.impl;

import com.hqyj.dao.RoleInfoDao;
import com.hqyj.pojo.RoleInfo;
import com.hqyj.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RoleInfoServoceImpl
 * @description
 * @author:duanli
 * @createDate:2020.10.23 14:04
 */
@Service
public class RoleInfoServoceImpl implements RoleInfoService {
    //注入RoleInfoDao
    @Autowired
    private RoleInfoDao roleInfoDao;

    //注入redis
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<RoleInfo> select() {
        String key = "roleList";
        List<RoleInfo> roleInfoList = null;
        //去缓存中查看是否有数据
        if (redisTemplate.opsForValue().get(key)!=null){
            //2、取出缓存数据
            roleInfoList = (List<RoleInfo>) redisTemplate.opsForValue().get(key);
        }else {
            //3、从数据库中取出数据
            roleInfoList = roleInfoDao.findAll();
            //4、存入到缓存中
            redisTemplate.opsForValue().set(key,roleInfoList,10, TimeUnit.MINUTES);
        }
        return roleInfoList;
    }
}
