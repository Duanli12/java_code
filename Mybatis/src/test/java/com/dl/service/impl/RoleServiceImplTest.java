package com.dl.service.impl;

import com.dl.Utils.MybatisUtil;
import com.dl.dao.RoleDao;
import com.dl.dao.RolePermissionDao;
import com.dl.pojo.Permission;
import com.dl.pojo.Role;
import com.dl.pojo.RolePermission;
import com.dl.service.RoleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleServiceImplTest {
    private static SqlSession sqlSession;
    private RoleService roleService;


    @BeforeClass
    public static void init(){
        sqlSession = MybatisUtil.getSession();

    }

    @AfterClass
    public static void destroy() {
        sqlSession.close();
    }

    @Before
    public void setUp() throws Exception {
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        RolePermissionDao rolePermissionDao = sqlSession.getMapper(RolePermissionDao.class);
        roleService = new RoleServiceImpl(roleDao,rolePermissionDao);

    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() {
        Set<Role> roleList = roleService.getAll();
        System.out.println(roleList);
    }

    @Test
    public void addOne() {
        Role role =new Role();
        role.setName("管理员");
        role.setState(0);
        Set<Permission> permissions =new HashSet<>();
        Permission p1 = new Permission();
        p1.setPermissionId(4);
        permissions.add(p1);
        role.setPermissions(permissions);
        roleService.addOne(role);

    }

    @Test
    public void removeOne() {
    }

    @Test
    public void modifyOne() {
        Set<Integer> roleIds = new HashSet<>();
        roleIds.add(1);
        roleIds.add(2);
        roleService.removeSome(roleIds);
    }
}