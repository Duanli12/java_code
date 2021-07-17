package com.dl.service.impl;

import com.dl.Utils.MybatisUtil;
import com.dl.dao.UserDao;
import com.dl.dao.UserRoleDao;
import com.dl.pojo.Role;
import com.dl.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    private UserServiceImpl userService;
   static private SqlSession sqlSession;

    @BeforeClass
    public static void init(){
        sqlSession = MybatisUtil.getSession();

    }

    @AfterClass
    public static void destroy(){
        sqlSession.close();
    }


    @Before
    public void setUp() throws Exception {
       /* SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try(InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")){
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            //使用sqlSessionFactory打开数据库会话
            sqlSession = sqlSessionFactory.openSession(true);
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            UserRoleDao userRoleDao = sqlSession.getMapper(UserRoleDao.class);
           // UserDao userDao = sqlSession.getMapper(UserDao.class);
            //创建一个userService
            userService = new UserServiceImpl(userDao,userRoleDao);
        }catch (IOException e){
            e.printStackTrace();
        }*/
       //用MybatisUtil工具类后的代码

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        UserRoleDao userRoleDao = sqlSession.getMapper(UserRoleDao.class);
        // UserDao userDao = sqlSession.getMapper(UserDao.class);
        //创建一个userService
        userService = new UserServiceImpl(userDao,userRoleDao);
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAll() {
        Set<User> userSet = userService.getAll();
        System.out.println(userSet);
    }

    @Test
    public void addOne() {
        User user = new User();
        user.setName("蔡文姬");
        user.setUsername("cwj");
        user.setPassword("123");
        user.setSex("10");
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(2);
        role.setName("普通用户");
        roles.add(role);
        user.setRoles(roles);
        userService.addOne(user);

        Set<User> users = userService.getAll();
        for (User item : users){
            if (item.getName()=="蔡文姬"){
                Set<Role> itemRoles = item.getRoles();
                assertEquals(itemRoles.size(),2);
            }
        }
    }

    @Test
    public void removeOne() {
    }

    @Test
    public void modifyOne() {
        Set<User> users = userService.getAll();
        for(User user:users){
            if (user.getUserId()==1){
                user.setPassword("111111");
                user.setName(null);
                userService.modifyOne(user);
            }
        }
    }
    @Test
    public  void removeSomeTest(){
        Set<Integer> userIds = new HashSet<>();
        userIds.add(1);
        userIds.add(2);
        userService.removeSome(userIds);

    }

    @Test
    public void getUserThatAddressLikeTest(){
        String address="二环路";
        Set<User> users = userService.getUserThatAddressLike(address);
        System.out.println(users);

    }

    @Test
    public void getUserWithMultiConditionTest(){
        System.out.println(userService.getUserWithMultiCondition("翠",null,null));
        System.out.println(userService.getUserWithMultiCondition(null,"189",null));
        System.out.println(userService.getUserWithMultiCondition("花","8888",null));
        System.out.println(userService.getUserWithMultiCondition("张","8888","普通"));
        System.out.println(userService.getUserWithMultiCondition("三","189","普通"));
    }
}