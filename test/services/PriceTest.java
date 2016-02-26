package services;

import com.jsnu.yls.graduation.service.Impl.PriceServiceImpl;
import com.jsnu.yls.graduation.service.Impl.RecordServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/2/25.
 */
public class PriceTest {

    private ApplicationContext applicationContext;
    private RecordServiceImpl recordService;
    private PriceServiceImpl priceService;

    {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        recordService = (RecordServiceImpl) applicationContext.getBean("recordService");
        priceService = (PriceServiceImpl) applicationContext.getBean("priceService");
    }

    @Test
    public void testUpdatePrice() {
        priceService.updatePrice(5);
    }

    @Test
    public void testGetTotalIncome(){
        System.out.println(priceService.getTotalIncome());
    }
}
