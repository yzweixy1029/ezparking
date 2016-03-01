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
 *
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

    @ResponseBody
    @RequestMapping(value = "/getSortedRecords/{order}")
    public List<Record> getRecords(@PathVariable String order) {
        String[] params = PropertyUtil.getFiledName(new Record());
        List<Record> records = new ArrayList<>();
        for (String s : params) {
            if (order.equals(s)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                records = recordService.getRecords(order);
                for (Record r : records) {
                    dateFormat.format(r.getStartTime());
                }
                break;
            }
        }
        return records;
    }



}
