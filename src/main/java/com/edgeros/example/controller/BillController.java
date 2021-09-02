package com.edgeros.example.controller;

import com.edgeros.example.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Acopay Demo: Demo Bill Controller
 *
 * @since 1.0.0
 */
@RestController
public class BillController {
    @Autowired
    private BillService billService;

    /**
     * 获取对账单信息
     *
     * @param billDate 请求对账单的日期
     * @return void
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET,value = "/your/path/to/bill")
    public void bill(@RequestParam(required = false) String billDate) {
        billService.bill(billDate);
    }
}
