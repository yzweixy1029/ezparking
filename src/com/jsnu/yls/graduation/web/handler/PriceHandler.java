package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.service.Impl.PriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *
 *
 * Created by Administrator on 2016/2/25.
 */
@Controller
public class PriceHandler {

    @Autowired
    private PriceServiceImpl priceService;

    @ModelAttribute
    public void getPrice(@RequestParam(value = "id", required = false) Integer id,
                          Map<String, Object> map) {
        if (id != null) {
            map.put("price", priceService.getPrice());
        }
    }

    /**
     * 价格情况页
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getPrice")
    public String getPrice(Map<String ,Object> map){
        map.put("price",priceService.getPrice());
        return "";
    }



}
