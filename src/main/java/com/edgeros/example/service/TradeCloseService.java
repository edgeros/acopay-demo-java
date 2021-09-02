package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.AcopayClient;
import com.edgeros.pay.DefaultAcopayClient;
import com.edgeros.pay.exception.AcopayException;
import com.edgeros.pay.model.request.AcopayTradeCloseRequestModel;
import com.edgeros.pay.model.response.AcopayResponse;
import com.edgeros.pay.model.response.AcopayTradeCloseResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Acopay Demo: Trade Close Service
 *
 * @since 1.0.0
 */
@Service
public class TradeCloseService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(TradeCloseService.class);

    /**
     * 交易关单
     *
     * @param tradeCloseRequestModel 关单请求参数
     */
    public void tradeClose(AcopayTradeCloseRequestModel tradeCloseRequestModel) {

        try {
            // 创建 AcopayClient 实例
            AcopayClient acopayClient = new DefaultAcopayClient(acopayParam.getAcopayPublicKey(),
                    acopayParam.getMchPrivateKey(), acopayParam.getMchNo());
            // 发送关单请求
            AcopayResponse<AcopayTradeCloseResponseModel> acopayTradeCloseAcopayResponse = acopayClient
                    .tradeClose(tradeCloseRequestModel);
            // 判断请求是否成功
            if (acopayTradeCloseAcopayResponse.statusSuccessful()) {
                log.info("关单接口返回：{}", acopayTradeCloseAcopayResponse);
                // 获取业务数据
                AcopayTradeCloseResponseModel data = acopayTradeCloseAcopayResponse.getData();
                // 获取业务状态码
                String code = data.getCode();
                // 根据业务状态码进行商户业务处理

            } else {
                log.info("关单接口业务失败，返回状态码：{}， 返回信息：{}， 异常信息：{}",
                        acopayTradeCloseAcopayResponse.getStatus(), acopayTradeCloseAcopayResponse.getMessage(),
                        acopayTradeCloseAcopayResponse.getFieldErrors());
            }
        } catch (AcopayException e) {
            // 进行异常处理
            log.error("关单接口请求异常", e);
        }
    }
}
