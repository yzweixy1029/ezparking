package services;

import com.jsnu.yls.graduation.dal.impl.ParkingDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Admin;
import com.jsnu.yls.graduation.service.Impl.AdminServiceImpl;
import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/25.
 */
public class AdminTest {

    private ApplicationContext applicationContext;
    private AdminServiceImpl adminService;
    {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        adminService = (AdminServiceImpl) applicationContext.getBean("adminService");
    }

    @Test
    public void testAdd(){
        Admin admin = new Admin();
        admin.setUserName("chenwei");
        admin.setPassword("103122");
        adminService.saveOrUpdateAccount(admin);
    }

    @Test
    public void testLogin(){
        Admin admin = adminService.login(new Admin("chenwei","103122"));
        System.out.println(admin);
    }

}
