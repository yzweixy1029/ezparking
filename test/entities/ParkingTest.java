package entities;

import com.jsnu.yls.graduation.dal.impl.ParkingDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Parking;
import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 停车位模块实体类单元测试
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
    public void testCreate(){
        Parking parking = new Parking();
        parking.setParkingID("C1");
        parking.setRegion("C");
        parking.setStatus(1);
        parkingDAO.saveEntity(parking);
    }

    @Test
    public void testAutoCreate(){
        parkingService.addParking("A");
    }

}
