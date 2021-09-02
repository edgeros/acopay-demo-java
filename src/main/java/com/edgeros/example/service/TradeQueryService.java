package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.AcopayClient;
import com.edgeros.pay.DefaultAcopayClient;
import com.edgeros.pay.exception.AcopayException;
import com.edgeros.pay.model.request.AcopayTradeQueryRequestModel;
import com.edgeros.pay.model.response.AcopayResponse;
import com.edgeros.pay.model.response.AcopayTradeQueryResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Acopay Demo: Trade Query Service
 *
 * @since 1.0.0
 */
@Service
public class TradeQueryService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(TradeQueryService.class);

    /**
     * 交易查询
     *
     * @param transactionNo 翼辉订单号
     * @param mchTradeNo 商户订单号
     */
    public void tradeQuery(String transactionNo, String mchTradeNo) {
        // 查询支付订单结果请求参数
        AcopayTradeQueryRequestModel tradeQueryRequestModel = new AcopayTradeQueryRequestModel();
        // 实际业务中指定查询的翼辉订单号
        tradeQueryRequestModel.setTransactionNo(transactionNo);
        // 实际业务中查询商户的订单号
        tradeQueryRequestModel.setMchTradeNo(mchTradeNo);

        AcopayResponse<AcopayTradeQueryResponseModel> tradeQueryAcopayResponse = null;
        try {
            // 创建 AcopayClient 实例
            AcopayClient acopayClient = new DefaultAcopayClient(acopayParam.getAcopayPublicKey(),
                    acopayParam.getMchPrivateKey(), acopayParam.getMchNo());
            // 发送交易查询请求
            tradeQueryAcopayResponse = acopayClient.tradeQuery(tradeQueryRequestModel);
        } catch (AcopayException e) {
            // 进行异常处理
            log.error("支付结果查询异常", e);
        }
        if (!Objects.isNull(tradeQueryAcopayResponse)) {
            if (tradeQueryAcopayResponse.statusSuccessful()) {
                log.info("翼辉支付平台返回：{}", tradeQueryAcopayResponse);
                // 获取业务数据
                AcopayTradeQueryResponseModel data = tradeQueryAcopayResponse.getData();
                // 业务请求成处理

            } else {
                log.info("支付结果查询业务失败，返回状态码：{}，返回信息：{}， 异常信息：{}",
                        tradeQueryAcopayResponse.getStatus(), tradeQueryAcopayResponse.getMessage(),
                        tradeQueryAcopayResponse.getFieldErrors());
            }
        }
    }
}
