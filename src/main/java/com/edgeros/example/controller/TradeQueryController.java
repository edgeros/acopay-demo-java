package com.edgeros.example.controller;

import com.edgeros.example.service.TradeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Acopay Demo: Demo Trade Query Controller
 *
 * @since 1.0.0
 */
@RestController
public class TradeQueryController {
    @Autowired
    private TradeQueryService tradeQueryService;

    /**
     * 交易查询
     *
     * @param transactionNo 翼辉订单号
     * @param mchTradeNo 商户订单号
     * @return void
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET,value = "/your/path/to/trade/query")
    public void tradeQuery(@RequestParam(value = "transactionNo", required = false)String transactionNo,
                           @RequestParam(value = "mchTradeNo", required = false)String mchTradeNo) {
        tradeQueryService.tradeQuery(transactionNo, mchTradeNo);
    }
}
