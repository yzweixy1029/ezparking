package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.persistence.entities.Record;
import com.jsnu.yls.graduation.plugin.utils.PropertyUtil;
import com.jsnu.yls.graduation.service.Impl.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 停车记录模块控制层
 * <p>
 * Created by WeiXY on 2016/2/22.
 */
@Controller
public class RecordHandler {

    @Autowired
    private RecordServiceImpl recordService;

    @ModelAttribute
    public void getRecord(@RequestParam(value = "id", required = false) Integer id,
                          Map<String, Object> map) {
        if (id != null) {
            map.put("record", recordService.getRecord(id));
        }
    }

    /**
     * ajax停车记录排序
     *
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSortedRecords/{order}")
    public List<Record> getRecords(@PathVariable String order) {
        String[] params = PropertyUtil.getFiledName(new Record());
        List<Record> records = new ArrayList<>();
        for (String s : params) {
            if (order.equals(s)) {
                records = recordService.getRecords(order);
                break;
            }
        }
        return records;
    }


    /**
     * 停车记录页
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getAllRecords")
    public String getRecords(Map<String, Object> map) {
        map.put("records", recordService.getRecords());
        return "record/record_list";
    }


}
