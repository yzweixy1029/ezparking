package services;

import com.jsnu.yls.graduation.dal.impl.ParkingDAOImpl;
import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 停车位模块及模拟停车单元测试
 *
 * Created by WeiXY on 2016/2/22.
 */
public class ParkingTest {

    private ApplicationContext applicationContext;
    private ParkingDAOImpl parkingDAO;
    private ParkingServiceImpl parkingService;

    {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        parkingService = (ParkingServiceImpl) applicationContext.getBean("parkingService");
        parkingDAO = (ParkingDAOImpl) applicationContext.getBean("parkingDAO");
    }

    @Test
    public void testAddParking(){
        parkingService.addParking("C");
    }

    @Test
    public void testPark(){
        parkingService.park("苏B909XF");
    }

    @Test
    public void testLeave(){
        parkingService.leave("苏BC852D");
    }

}
