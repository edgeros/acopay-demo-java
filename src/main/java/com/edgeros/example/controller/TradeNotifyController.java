package com.edgeros.example.controller;

import com.edgeros.example.service.TradeNotifyService;
import com.edgeros.pay.model.AcopayNotifyResponseModel;
import com.edgeros.pay.model.AcopayTradeNotifyReceiveModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.edgeros.pay.constant.AcopayConstant.NOTIFY_FAIL;
import static com.edgeros.pay.constant.AcopayConstant.NOTIFY_SUCCESS;

/**
 * Acopay Demo: Demo Trade Notify Controller
 *
 * @since 1.0.0
 */
@RestController
public class TradeNotifyController {

    @Autowired
    private TradeNotifyService tradeNotifyService;

    private static final Logger log = LoggerFactory.getLogger(TradeNotifyController.class);

    /**
     * 接收翼辉支付异步通知
     *
     * @param tradeNotifyReceiveModel
     * @return com.edgeros.pay.model.AcopayNotifyResponseModel
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST,value = "/your/path/to/trade/notify")
    public AcopayNotifyResponseModel reciveNotify(@RequestBody AcopayTradeNotifyReceiveModel tradeNotifyReceiveModel) {
        // demo程序使用 jackson 序列化参数，翼辉支付平台发送的参数为带下划线的，
        // AcopayTradeNotifyReceiveModel类 为驼峰命命名规则
        // 商户系统如果不是用 jackson 序列化参数，则需要使用其它方式接收参数 参见：RefundNotifyController
        AcopayNotifyResponseModel notifyResponseModel = new AcopayNotifyResponseModel();
        try {
            tradeNotifyService.handleNotify(tradeNotifyReceiveModel);
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
