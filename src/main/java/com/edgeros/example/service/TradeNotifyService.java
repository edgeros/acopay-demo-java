package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.model.AcopayTradeNotifyReceiveModel;
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
     * @param tradeNotifyModelRequest 翼辉支付异步通知参数
     * @throws Exception
     */public void handleNotify(AcopayTradeNotifyReceiveModel tradeNotifyModelRequest) throws Exception {

        // 翼辉支付平台发送的的参数是带下划线的 验证签名时需转换为下划线的参数名
        Map<String, Object> requestMap = AcopayCommonUtil.beanToLowerUnderscoreMap(tradeNotifyModelRequest);
        AcopaySignUtil.verifySign(requestMap, acopayParam.getAcopayPublicKey());
        // 没有异常进行业务处理
        // 进行商户业务处理
        log.info("接收支付结果异步通知：{}", tradeNotifyModelRequest);

    }

}
