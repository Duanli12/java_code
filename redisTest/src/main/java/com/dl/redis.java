package com.dl;

import redis.clients.jedis.Jedis;

/**
 * @ClassName redis
 * @description
 * @author:duanli
 * @createDate:2020.10.23 11:14
 */
public class redis {
    public static void main(String[] args) {
      /*  //创建连接
        Jedis jedis = new Jedis("127.0.0.1");
//添加数据
        jedis.set("name","hello");
//关闭连接
        jedis.close();
        */
        setObject();
    }

    //存对象
    public static void setObject(){
        deparement d = new deparement();
        d.setName("组织部");
        //1、创建redis连接
        Jedis jedis = new Jedis("127.0.0.1" );
        //存对象
        jedis.set("key".getBytes(),serializUtil.serialize(d));
        //取对象
        byte[] by = jedis.get("key".getBytes());
        deparement ddd = (deparement) serializUtil.unserialize(by);
        System.out.println("部门名称："+ddd.getName());
        jedis.close();


    }

}
