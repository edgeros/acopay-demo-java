package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.model.AcopayRefundNotifyReceiveModel;
import com.edgeros.pay.util.AcopayCommonUtil;
import com.edgeros.pay.util.AcopaySignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Acopay Demo: Refund Notify Service
 *
 * @since 1.0.0
 */
@Service
public class RefundNotifyService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(RefundNotifyService.class);

    /**
     * 根据返回的退款结果 处理商户业务
     *
     * @param refundNotifyReceiveModel 退款通知参数
     * @throws Exception
     */
    public void handleNotify(AcopayRefundNotifyReceiveModel refundNotifyReceiveModel) throws Exception {
        // 翼辉支付平台发送的的参数是带下划线的 验证签名时需转换为下划线的参数名
        AcopaySignUtil.verifySign(AcopayCommonUtil.beanToLowerUnderscoreMap(refundNotifyReceiveModel), acopayParam.getAcopayPublicKey());
        // 没有异常进行业务处理
        // 进行商户业务处理
        log.info("接收退款结果异步通知{}", refundNotifyReceiveModel);

    }
}
