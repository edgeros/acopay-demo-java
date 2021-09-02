package com.edgeros.example.controller;

import com.edgeros.example.service.RefundQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Acopay Demo: Demo Refund Query Controller
 *
 * @since 1.0.0
 */
@RestController
public class RefundQueryController {
    @Autowired
    private RefundQueryService refundQueryService;

    /**
     * 退款查询
     *
     * @param refundNo 翼辉退款订单号
     * @param mchRefundNo 商户退款订单号
     * @return void
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET,value = "/your/path/to/refund/query")
    public void refundQuery(@RequestParam(value = "refundNo", required = false) String refundNo,
                            @RequestParam(value = "mchRefundNo", required = false) String mchRefundNo) {
        refundQueryService.refundQuery(refundNo, mchRefundNo);
    }
}
