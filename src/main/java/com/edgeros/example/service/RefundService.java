package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.AcopayClient;
import com.edgeros.pay.DefaultAcopayClient;
import com.edgeros.pay.exception.AcopayException;
import com.edgeros.pay.model.request.AcopayRefundRequestModel;
import com.edgeros.pay.model.response.AcopayRefundResponseModel;
import com.edgeros.pay.model.response.AcopayResponse;
import com.edgeros.pay.util.AcopayCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Acopay Demo: Refund Service
 *
 * @since 1.0.0
 */
@Service
public class RefundService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(RefundService.class);

    /**
     * 发起退款请求
     *
     * @param refundRequestModel 退款请求参数
     */
    public void refund(AcopayRefundRequestModel refundRequestModel) {

        // 根据商户实际业务进行参数校验
        // 根据商户订单规则生成退款单号
        refundRequestModel.setMchRefundNo(AcopayCommonUtil.getRandomString(40));
        // 填写商户的退款通知地址
        refundRequestModel.setNotifyUrl(acopayParam.getRefundNotifyUrl());

        try {
            // 实例化 AcopayClient
            AcopayClient acopayClient = new DefaultAcopayClient(acopayParam.getAcopayPublicKey(),
                    acopayParam.getMchPrivateKey(), acopayParam.getMchNo());
            // 发起退款请求
            AcopayResponse<AcopayRefundResponseModel> refundAcopayResponse = acopayClient.refund(refundRequestModel);

            if (refundAcopayResponse.statusSuccessful()){
                log.info("退款接口返回：{}", refundAcopayResponse);
                // 获取业务数据
                AcopayRefundResponseModel data = refundAcopayResponse.getData();
                // 业务请求成功处理
            } else {
                // 业务请求失败处理
                log.info("退款接口业务失败，返回状态码：{}， 返回信息：{}， 异常信息：{}",
                        refundAcopayResponse.getStatus(), refundAcopayResponse.getMessage(),
                        refundAcopayResponse.getFieldErrors());
            }
        } catch (AcopayException e) {
            // 进行异常处理
            log.error("退款请求异常", e);
        }
    }
}
