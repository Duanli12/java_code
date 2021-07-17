package com.hqyj.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

/**
 * @ClassName MdFive
 * @description
 * @author:duanli
 * @createDate:2020.10.22 13:28
 */
@Component
public class MdFive {
    public String encrypt(String password,String saltValue){
        Object salt = new Md5Hash(saltValue);
        Object result = new SimpleHash("MD5",password,salt,1024);
        return result+"";
    }

}
