package com.edgeros.example.controller;

import com.edgeros.example.service.RefundService;
import com.edgeros.pay.model.request.AcopayRefundRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Acopay Demo: Demo Refund Controller
 *
 * @since 1.0.0
 */
@RestController
public class RefundController {
    @Autowired
    private RefundService refundService;

    /**
     * 申请退款
     *
     * @param refundRequestModel 申请退款参数
     * @return void
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST, value = "/your/path/to/refund")
    public void refund(@RequestBody AcopayRefundRequestModel refundRequestModel) {
        refundService.refund(refundRequestModel);

    }
}
