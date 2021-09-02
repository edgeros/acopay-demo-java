package com.edgeros.example.controller;

import com.edgeros.example.service.TradeCloseService;
import com.edgeros.pay.model.request.AcopayTradeCloseRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Acopay Demo: Demo Trade Close Controller
 *
 * @since 1.0.0
 */
@RestController
public class TradeCloseController {
    @Autowired
    private TradeCloseService tradeCloseService;

    /**
     * 关闭交易
     *
     * @param tradeCloseRequestModel
     * @return void
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST,value = "/your/path/to/trade/close")
    public void tradeColse(@RequestBody AcopayTradeCloseRequestModel tradeCloseRequestModel) {
        tradeCloseService.tradeClose(tradeCloseRequestModel);
    }
}
