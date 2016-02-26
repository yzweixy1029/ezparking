package com.jsnu.yls.graduation.service.Impl;

import com.jsnu.yls.graduation.dal.impl.RecordDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Record;
import com.jsnu.yls.graduation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 停车记录业务逻辑层
 * <p>
 * Created by chenwei on 2016/2/22.
 */
@Service(value = "recordService")
public class RecordServiceImpl implements BaseService<Record> {

    @Autowired
    private RecordDAOImpl recordDAO;

    /**
     * 获取所有停车单
     *
     * @return
     */
    public List<Record> getRecords() {
        List<Record> records = recordDAO.getAllRecords();
        return records;
    }




}
