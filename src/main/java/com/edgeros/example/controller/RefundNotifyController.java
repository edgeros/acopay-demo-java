package com.edgeros.example.controller;

import com.edgeros.example.service.RefundNotifyService;
import com.edgeros.pay.model.AcopayNotifyResponseModel;
import com.edgeros.pay.model.AcopayRefundNotifyReceiveModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.edgeros.pay.constant.AcopayConstant.NOTIFY_FAIL;
import static com.edgeros.pay.constant.AcopayConstant.NOTIFY_SUCCESS;

/**
 * Acopay Demo: Demo Refund Notify Controller
 *
 * @since 1.0.0
 */
@RestController
public class RefundNotifyController {

    @Autowired
    private RefundNotifyService refundNotifyService;

    private static final Logger log = LoggerFactory.getLogger(TradeNotifyController.class);

    /**
     * 接收翼辉退款异步通知
     *
     * @param httpServletRequest
     * @return com.edgeros.pay.model.AcopayNotifyResponseModel
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST, value = "/your/path/to/refund/notify")
    public AcopayNotifyResponseModel reciveNotify(HttpServletRequest httpServletRequest) {

        // demo程序使用 jackson 序列化参数，翼辉支付平台发送的参数为带下划线的，
        // AcopayTradeNotifyReceiveModel类 为驼峰命命名规则
        // 商户系统如果使用 jackson 序列化参 参见：TradeNotifyController
        AcopayNotifyResponseModel notifyResponseModel = new AcopayNotifyResponseModel();
        StringBuilder receive = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(),
                "UTF-8"))) {
            while ((line = reader.readLine()) != null) {
                receive.append(line);
            }
        } catch (IOException e) {
            log.error("接收翼辉退款结果异步通知", e);
            notifyResponseModel.setStatus(NOTIFY_FAIL);
            notifyResponseModel.setMessage("接收异步通知失败");
            return notifyResponseModel;
        }
        log.info("接收翼辉退款结果异步通知：{}", receive);

        try {
            // 翼辉发送的异步通知是下划线格式的 接收的对象是驼峰格式的
            AcopayRefundNotifyReceiveModel refundNotifyReceiveModel = new ObjectMapper().readValue(receive.toString(),
                    AcopayRefundNotifyReceiveModel.class);
            refundNotifyService.handleNotify(refundNotifyReceiveModel);
            notifyResponseModel.setStatus(NOTIFY_SUCCESS);
            notifyResponseModel.setMessage("成功");
        } catch (Exception e) {
            log.error("接收异步通知失败：{}", e.getMessage());
            notifyResponseModel.setStatus(NOTIFY_FAIL);
            notifyResponseModel.setMessage("接收异步通知失败");
        }
        return notifyResponseModel;
    }
}
