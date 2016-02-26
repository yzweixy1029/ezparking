package services;

import com.jsnu.yls.graduation.persistence.entities.Record;
import com.jsnu.yls.graduation.service.Impl.RecordServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 2016/2/25.
 */
public class RecordTest {

    private ApplicationContext applicationContext;
    private RecordServiceImpl recordService;

    {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        recordService = (RecordServiceImpl) applicationContext.getBean("recordService");
    }

    @Test
    public void testGetRecords() {
        List<Record> records = recordService.getRecords();
        for (Record r : records){
            System.out.println(r);
        }
    }
}
