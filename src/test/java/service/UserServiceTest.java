package service;

import dao.UserMapper;
import dao.condition.RoleCondition;
import dao.condition.UserCondition;
import model.Resource;
import model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wenqing on 2016/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserMapper userDao;

    @Test
    public void selectUsers() {
        UserCondition condition = new UserCondition();
        condition.setSortby("username");
        condition.setUsername("admin");
        //condition.setRole("admin");
        //
        System.out.println(userService.query(condition).size());
        System.out.println(userService.query(condition));
    }

    @Test
    public void selectRoles() {
        RoleCondition condition = new RoleCondition();
        condition.setRole("admin");
        System.out.println(roleService.query(condition));
    }

    @Test
    public void selectRolesById() {
        System.out.print(userDao.selectRolesById(new Long(2)));
    }

    @Test
    public void selectRolesByName() {

        System.out.print(userDao.selectRolesByUsername("test1"));
    }

    @Test
    public void selectResourceByRoleId() {
        System.out.print(resourceService.queryByRoleId(new Long(1)));
    }
}
