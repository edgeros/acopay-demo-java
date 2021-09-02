package com.edgeros.example.service;

import com.edgeros.example.property.AcopayParam;
import com.edgeros.pay.AcopayClient;
import com.edgeros.pay.DefaultAcopayClient;
import com.edgeros.pay.exception.AcopayException;
import com.edgeros.pay.model.request.AcopayBillRequestModel;
import com.edgeros.pay.model.response.AcopayBillResponseModel;
import com.edgeros.pay.model.response.AcopayResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.edgeros.example.constant.DemoConstant.SUCCESS;

/**
 * Acopay Demo: Bill Service
 *
 * @since 1.0.0
 */
@Service
public class BillService {

    @Autowired
    private AcopayParam acopayParam;

    private static final Logger log = LoggerFactory.getLogger(BillService.class);

    /**
     * 获取指定日期的账单
     *
     * @param billDate 账单日期
     */
    public void bill(String billDate) {
        // 请求对账单信息实例
        AcopayBillRequestModel billRequestModel = new AcopayBillRequestModel();
        // 获取前一天的对账单信息 可获取商户三个月内的交易账单信息 下载日期时区为东八区
        if (StringUtils.isBlank(billDate)) {
            LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Shanghai")).minusDays(1);
            billDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        billRequestModel.setBillDate(billDate);
        AcopayResponse<AcopayBillResponseModel> acopayBillResponse = null;
        try {
            // 创建 AcopayClient 实例
            AcopayClient acopayClient = new DefaultAcopayClient(acopayParam.getAcopayPublicKey(),
                    acopayParam.getMchPrivateKey(), acopayParam.getMchNo());
            // 获取对账单信息
            acopayBillResponse = acopayClient.bill(billRequestModel);
        } catch (AcopayException e) {
            // 根据商户业务需求进行异常处理
            log.error("请求对账单信息出错", e);
        }
        // 正常流程进行业务处理
        if (!Objects.isNull(acopayBillResponse)) {
            log.info("接收下载对账单信息：{}", acopayBillResponse);
            if (acopayBillResponse.statusSuccessful()) {
                // 获取业务数据
                AcopayBillResponseModel data = acopayBillResponse.getData();
                // NO_BILL ：账单不存在，SUCCESS ：成功，BILL_NOT_EXIST ：对账单未生成
                if (StringUtils.equals(data.getCode(), SUCCESS)) {
                    // 获取下载对账单地址
                    String billDownloadUrl = data.getUrl();
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<String> msg = restTemplate.getForEntity(billDownloadUrl, String.class);
                    log.info("接收账单：{}", msg.getBody());
                    // 进行数据校验业务处理
                } else {
                    // 根据 code 值进行业务处理
                }
            } else {
                // 进行业务处理
            }
        }
    }
}
