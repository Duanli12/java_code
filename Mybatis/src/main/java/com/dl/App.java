package com.dl;

import com.dl.dao.UserDao;
import com.dl.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        //通过SqlSessionFactoryBuilder读取mybatis基础配置文件来构建sqlSessionFactory
       /* SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try(InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")){
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            //sqlSession是通过sqlSessionFactory来创建出来的
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //核心目标:通过sqlSession拿到映射的UserDao接口的实现对象

            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //通过mybatis帮我们实现的UserDao查询数据
            List<User> userList = userDao.queryAll();
            System.out.println(userList);*/
           /* //添加
            User user = new User();
            user.setName("王五");
            user.setSex(10);
            user.setAddress("幸福路");
            user.setPhone("13988701655");
            int  result =userDao.insert(user);
            System.out.println(result);*/

            //更改
           /* User user = new User();
            user.setId(1);
            user.setName("安琪拉");
            user.setSex(10);
            user.setAddress("永州路");
            user.setPhone("12563444444");
            int re = userDao.update(user);
            System.out.println(re);*/

            //删除
           /* User user = new User();
            user.setId(2);
            int result = userDao.delete(user);
            System.out.println(result);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
