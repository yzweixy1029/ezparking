package com.jsnu.yls.graduation.service.Impl;

import com.jsnu.yls.graduation.dal.impl.RecordDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Record;
import com.jsnu.yls.graduation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 停车记录模块业务逻辑层
 * <p>
 * Created by WeiXY on 2016/2/22.
 */
@Service(value = "recordService")
public class RecordServiceImpl implements BaseService<Record> {

    @Autowired
    private RecordDAOImpl recordDAO;

    /**
     * get
     *
     * @param id
     * @return
     */
    public Record getRecord(Integer id){
        return recordDAO.getEntity(id);
    }

    /**
     * 获取所有停车单
     *
     * @return
     */
    public List<Record> getRecords() {
        return recordDAO.getAllRecords();
    }

    /**
     * 重载排序方法
     *
     * @param order
     * @return
     */
    public List<Record> getRecords(String order) {
        return recordDAO.getAllRecords(order);
    }


}
