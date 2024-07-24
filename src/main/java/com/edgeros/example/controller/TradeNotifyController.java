package com.edgeros.example.controller;

import com.alibaba.fastjson2.JSONObject;
import com.edgeros.example.property.AcopayParam;
import com.edgeros.example.service.TradeNotifyService;
import com.edgeros.pay.model.AcopayNotifyResponseModel;
import com.edgeros.pay.util.AcopaySignUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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
    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(TradeNotifyController.class);

    /**
     * 接收翼辉支付异步通知
     *
     * @return com.edgeros.pay.model.AcopayNotifyResponseModel
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST,value = "/your/path/to/trade/notify")
    public AcopayNotifyResponseModel reciveNotify(HttpServletRequest httpServletRequest) {
        AcopayNotifyResponseModel notifyResponseModel = new AcopayNotifyResponseModel();
        try {
            String json = IOUtils.toString(httpServletRequest.getInputStream(), StandardCharsets.UTF_8);
            log.info("接收翼辉支付结果异步通知：{}", json);
            Map<String,Object> params = JSONObject.parseObject(json, Map.class);
            //验证签名
            AcopaySignUtil.verifySign(params,acopayParam.getAcopayPublicKey());
            //TODO 处理后续业务
            tradeNotifyService.handleNotify(params);
            notifyResponseModel.setStatus(NOTIFY_SUCCESS);
            notifyResponseModel.setMessage("成功");
        } catch (Exception e) {
            log.error("接收异步通知失败：{}", e.getMessage());
            notifyResponseModel.setStatus(NOTIFY_FAIL);
            notifyResponseModel.setMessage("失败");
        }
        return notifyResponseModel;
    }
}
