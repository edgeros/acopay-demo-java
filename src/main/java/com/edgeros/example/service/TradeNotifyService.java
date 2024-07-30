package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.util.AcopayCommonUtil;
import com.edgeros.pay.util.AcopaySignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Acopay Demo: Trade Notify Service
 *
 * @since 1.0.0
 */
@Service
public class TradeNotifyService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(TradeNotifyService.class);

    /**
     * 根据翼辉返回的支付结果 处理商户业务
     *
     * @param params 翼辉支付异步通知参数
     * @throws Exception
     */public void handleNotify(Map<String,Object> params) throws Exception {
        // 进行商户业务处理
        log.info("接收支付结果异步通知：{}", params);

    }

}
