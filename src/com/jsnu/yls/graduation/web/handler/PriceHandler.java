package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.service.Impl.PriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 财务模块控制层
 *
 * Created by WeiXY on 2016/2/25.
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
    public String getPrice(Map<String, Object> map) {
        map.put("price", priceService.getPrice());
        return "";
    }

    /**
     * 更新价格
     *
     * @param price
     * @param map
     * @return
     */
    @RequestMapping(value = "/updatePrice")
    public String updatePrice(@RequestParam String price, Map<String, Float> map) {
        float newPrice = Float.valueOf(price);
        priceService.updatePrice(newPrice);
        map.put("price", newPrice);
        return "";
    }

    /**
     * 总收入统计页
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getIncome")
    public String getTotalIncome(Map<String, Float> map) {
        map.put("totalIncome", priceService.getTotalIncome());
        return "";
    }


}
