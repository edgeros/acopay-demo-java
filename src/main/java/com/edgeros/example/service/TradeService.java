package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.constant.AcopayConstant;
import com.edgeros.pay.model.AcopayTradeAppModel;
import com.edgeros.pay.util.AcopayCommonUtil;
import com.edgeros.pay.util.AcopaySignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.edgeros.example.constant.DemoConstant.DATETIME_PATTERN_RFC3339;

/**
 * Acopay Demo: Trade Service
 *
 * @since 1.0.0
 */
@Service
public class TradeService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(TradeService.class);

    /**
     * 获取 Web-SDK 调用支付下单接口所需参数
     *
     * @param tradeAppModel 商户业务数据
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws Exception
     */
    public Map<String, Object> getTradeParameter(AcopayTradeAppModel tradeAppModel) throws Exception {

        // 实际业务中根据请求参数返回业务相关的支付参数
        tradeAppModel.setAmountCurrency("CNY");
        tradeAppModel.setAppNo(acopayParam.getAppNo());
        tradeAppModel.setDescription("test pay ");
        tradeAppModel.setExtra("商户业务附加数据");
        tradeAppModel.setMchNo(acopayParam.getMchNo());
        tradeAppModel.setMchTradeNo(AcopayCommonUtil.getRandomString(40));
        // 填写商户的支付通知地址
        tradeAppModel.setNotifyUrl(acopayParam.getTradeNotifyUrl());
        tradeAppModel.setNonce(AcopayCommonUtil.getRandomString(32));
        tradeAppModel.setSignType(AcopayConstant.SIGN_TYPE);
        ZonedDateTime zonedDateTime = ZonedDateTime.now().plusMinutes(10);
        String timeExpire = zonedDateTime.format(DateTimeFormatter.ofPattern(DATETIME_PATTERN_RFC3339));
        tradeAppModel.setTimeExpire(timeExpire);
        // 翼辉接收参数为下划线格式 需要转换参数格式
        String sign = AcopaySignUtil.getSign(AcopayCommonUtil.beanToLowerUnderscoreMap(tradeAppModel),
                acopayParam.getMchPrivateKey());
        //  没有异常 进行商户业务处理 并返回给 Web-SDK 数据
        tradeAppModel.setSign(sign);

        log.info("返回 Web-SDK 支付参数：{}", tradeAppModel);
        // Web-SDK 需要下划线格式的参数 AcopayTradeAppModel 类中参数以驼峰方式命名
        return AcopayCommonUtil.beanToLowerUnderscoreMap(tradeAppModel);
    }
}
