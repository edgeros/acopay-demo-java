# Acopay demo for Java
  
- [产品官网](https://www.edgeros.com/edgeros/acopay/)   
- [SDK 说明](https://www.edgeros.com/edgeros/acopay/sdk/java/overview.html)    
- [API 文档](https://www.edgeros.com/edgeros/acopay/api/overview.html)  
- SDK 源码请参见 [GitHub SDK](https://github.com/edgeros/acopay-sdk-java) 或 [Gitee SDK](https://gitee.com/edgeros/acopay-sdk-java) 

## 说明
本示例展示了基于 Acopay Java SDK 调用翼辉支付 API 的基本逻辑。
- 示例基于 Acopay Java SDK 1.0.0 版本开发，Acopay Java SDK 需使用 JDK 1.7 及以上版本。   
- 示例使用 spring-boot 2.3 和 JDK 11 进行开发。  
- 示例中的 application-prod.yml 配置只是为了方便示例运行，真实产品中，建议您使用其他方式保障账户信息的安全性。  
- 示例中展示了怎么使用 Acopay Java SDK 调用一个 API 的主要步骤：  
  1.创建 DefaultAcopayClient 实例并初始化。  
  2.创建请求 API 所需参数对象 RequestModel，并设置对应的参数。  
  3.发起请求并处理响应或异常。  
```
    public void tradeQueryDemo() {

        // 1.创建 DefaultAcopayClient 实例并初始化。

        // 翼辉支付平台公钥
        String acopayPublicKey = "<acopayPublicKey>";
        // 商户私钥
        String mchPrivateKey = "<yourMchPrivateKey>";
        // 商户号
        String mchNo = "<yourMchNo>";
        // 初始化 AcopayClient
        AcopayClient acopayClient = new DefaultAcopayClient(acopayPublicKey, mchPrivateKey, mchNo);

        // 2.创建请求接口所需参数对象 RequestModel，并设置对应的参数。 
        AcopayTradeQueryRequestModel tradeQueryRequestModel = new AcopayTradeQueryRequestModel();
        // 实际业务中指定查询的翼辉订单号
        tradeQueryRequestModel.setTransactionNo("transactionNo");
        // 实际业务中查询商户的订单号
        tradeQueryRequestModel.setMchTradeNo("mchTradeNo");
        // 3.发起请求并处理响应或异常。
        AcopayResponse<AcopayTradeQueryResponseModel> tradeQueryAcopayResponse = acopayClient.tradeQuery(tradeQueryRequestModel);

    }
```
