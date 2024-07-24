package com.edgeros.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;


/**
 * Acopay Demo: Refund Notify Service
 *
 * @since 1.0.0
 */
@Service
public class RefundNotifyService {

    private static final Logger log = LoggerFactory.getLogger(RefundNotifyService.class);

    /**
     * 根据返回的退款结果 处理商户业务
     * @param params 退款通知参数
     * @throws Exception
     */
    public void handleNotify(Map<String,Object> params) throws Exception {
        // 进行商户业务处理
        log.info("接收退款结果异步通知{}", params);

    }
}
