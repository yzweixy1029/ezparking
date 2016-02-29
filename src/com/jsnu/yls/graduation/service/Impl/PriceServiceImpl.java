package com.jsnu.yls.graduation.service.Impl;

import com.jsnu.yls.graduation.dal.impl.PriceDAOImpl;
import com.jsnu.yls.graduation.dal.impl.RecordDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Price;
import com.jsnu.yls.graduation.persistence.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 价格业务逻辑层
 *
 * Created by chenwei on 2016/2/22.
 */
@Service(value = "priceService")
public class PriceServiceImpl {

    @Autowired
    private PriceDAOImpl priceDAO;
    @Autowired
    private RecordDAOImpl recordDAO;

    /**
     * 获取当前单价
     *
     * @return
     */
    public Price getPrice(){
        return priceDAO.getEntity(1);
    }

    /**
     * 更新价格
     *
     * @param price
     */
    public void updatePrice(float price){
        Price nowPrice = priceDAO.getEntity(1);
        nowPrice.setPrice(price);
        priceDAO.saveOrUpdateEntity(nowPrice);
    }

    /**
     * 获取当前总收入
     *
     * @return
     */
    public float getTotalIncome() {
        float totalIncome = 0;

        List<Record> records = recordDAO.getSettledRecords();
        for (Record r : records) {
            totalIncome += r.getCost();
        }

        return totalIncome;
    }

}
