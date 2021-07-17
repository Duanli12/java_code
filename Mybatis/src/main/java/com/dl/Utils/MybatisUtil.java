package com.dl.Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MybatisUtil
 * @description
 * @author:duanli
 * @createDate:2020/9/16 18:26
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = builder.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession(true);//true意味着打开数据库的自动提交
    }
}
