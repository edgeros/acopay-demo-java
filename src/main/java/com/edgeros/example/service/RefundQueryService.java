package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.AcopayClient;
import com.edgeros.pay.DefaultAcopayClient;
import com.edgeros.pay.exception.AcopayException;
import com.edgeros.pay.model.request.AcopayRefundQueryRequestModel;
import com.edgeros.pay.model.response.AcopayRefundQueryResponseModel;
import com.edgeros.pay.model.response.AcopayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Acopay Demo: Refund Query Service
 *
 * @since 1.0.0
 */
@Service
public class RefundQueryService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(RefundQueryService.class);

    /**
     * 查询退库结果
     *
     * @param refundNo 翼辉退款单号
     * @param mchRefundNo 商户退款单号
     */
    public void refundQuery(String refundNo, String mchRefundNo) {

        // 退款查询参数
        AcopayRefundQueryRequestModel refundQueryRequestModel = new AcopayRefundQueryRequestModel();
        // 实际业务中指定将要查询的商户退单号
        refundQueryRequestModel.setMchRefundNo(mchRefundNo);
        // 实际业务中指定将要查询的翼辉退单号
        refundQueryRequestModel.setRefundNo(refundNo);

        AcopayResponse<AcopayRefundQueryResponseModel> acopayRefundQueryResponse = null;
        try {
            // 创建 AcopayClient 实例
            AcopayClient acopayClient = new DefaultAcopayClient(acopayParam.getAcopayPublicKey(),
                    acopayParam.getMchPrivateKey(), acopayParam.getMchNo());
            // 发起退款查询请求
            acopayRefundQueryResponse = acopayClient.refundQuery(refundQueryRequestModel);
        } catch (AcopayException e) {
            // 进行异常处理
            log.error("退款结果查询接口异常", e);
        }
        // 正常流程进行业务处理
        if (!Objects.isNull(acopayRefundQueryResponse)) {
            if (acopayRefundQueryResponse.statusSuccessful()) {
                log.info("退款结果查询业务返回：{}", acopayRefundQueryResponse);
                // 获取业务数据
                AcopayRefundQueryResponseModel data = acopayRefundQueryResponse.getData();
                // 查询业务成功处理
            } else {
                // 查询业务异常处理
                log.info("退款结果查询业务失败，返回状态码：{}， 返回信息：{}， 异常信息：{}",
                        acopayRefundQueryResponse.getStatus(), acopayRefundQueryResponse.getMessage(),
                        acopayRefundQueryResponse.getFieldErrors());
            }
        }
    }
}
