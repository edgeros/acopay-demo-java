package com.edgeros.example.controller;

import com.edgeros.example.service.TradeService;
import com.edgeros.pay.model.AcopayTradeAppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Acopay Demo: Demo Trade Controller
 *
 * @since 1.0.0
 */
@RestController
@CrossOrigin()
public class TradeController {
    @Autowired
    private TradeService tradeService;

    /**
     * 获取 Web-SDK 所需的下单参数
     *
     * @param
     * @return java.util.Map
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST,value = "/your/path/to/trade")
    public Map getTradeParameter(@RequestBody AcopayTradeAppModel tradeRequestModel) throws Exception {
        return tradeService.getTradeParameter(tradeRequestModel);
    }
}
