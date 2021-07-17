package com.hqyj.dl.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName MD5Utils
 * @description
 * @author:duanli
 * @createDate:2020/9/26 9:27
 */
public class MD5Utils {
    //输入用户的明文密码
    //输入一个salt，加密的时候增强安全性
    //将用户明文密码加密
    static public String md5Hash(String password,String salt){
        //把字符串的salt转换成二进制内容的一个对象
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        //传入散列算法MD5，明文密码，salt。迭代次数
        SimpleHash simpleHash = new SimpleHash("MD5",password,
                byteSource,1024);
        return simpleHash.toString();//返回加密的字符串
    }


}
