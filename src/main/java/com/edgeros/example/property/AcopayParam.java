package com.edgeros.example.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Acopay Demo: Acopay Param
 *
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "acopay")
@Component
public class AcopayParam {

    /**
     * 商户号
     */
    private String mchNo;
    /**
     * 支付应用号
     */
    private String appNo;
    /**
     * 商户私钥字符串
     */
    private String mchPrivateKey;
    /**
     * 翼辉支付平台公钥字符串
     */
    private String acopayPublicKey;
    /**
     * 支付通知地址
     */
    private String tradeNotifyUrl;
    /**
     * 退款通知地址
     */
    private String refundNotifyUrl;

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getMchPrivateKey() {
        return mchPrivateKey;
    }

    public void setMchPrivateKey(String mchPrivateKey) {
        this.mchPrivateKey = mchPrivateKey;
    }

    public String getAcopayPublicKey() {
        return acopayPublicKey;
    }

    public void setAcopayPublicKey(String acopayPublicKey) {
        this.acopayPublicKey = acopayPublicKey;
    }

    public String getTradeNotifyUrl() {
        return tradeNotifyUrl;
    }

    public void setTradeNotifyUrl(String tradeNotifyUrl) {
        this.tradeNotifyUrl = tradeNotifyUrl;
    }

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }

    @Override
    public String toString() {
        return "AcopayParam{" +
                "mchNo='" + mchNo + '\'' +
                ", appNo='" + appNo + '\'' +
                ", mchPrivateKey='" + mchPrivateKey + '\'' +
                ", acopayPublicKey='" + acopayPublicKey + '\'' +
                ", tradeNotifyUrl='" + tradeNotifyUrl + '\'' +
                ", refundNotifyUrl='" + refundNotifyUrl + '\'' +
                '}';
    }
}
