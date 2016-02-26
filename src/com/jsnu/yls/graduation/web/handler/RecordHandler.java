package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.service.Impl.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 停车记录Handler
 *
 * Created by Administrator on 2016/2/22.
 */
@Controller
public class RecordHandler {

    @Autowired
    private RecordServiceImpl recordService;

}
