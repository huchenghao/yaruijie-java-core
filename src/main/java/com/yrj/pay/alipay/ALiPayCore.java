package com.yrj.pay.alipay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * 
 * @ClassName: ALiPayCore
 * @Description: alipay pay core
 * @author: huchenghao
 * @date: 2018年8月28日 下午6:47:50
 * 当前版本:3.3.1  生成时间:2018-07-05 22:12:46
 * alipay-sdk-java-3.3.1.jar
 */
public class ALiPayCore {
	private final static Logger logger = Logger.getLogger(ALiPayCore.class);
	/**
	 * 
	 * @Title: createAliPayStr
	 * @Description: JAVA服务端SDK生成APP支付订单信息
	 * @param aliPayMap
	 * [必传]URL:支付宝网关（固定）<br/>
	 * [必传]APP_ID:APPID即创建应用后生成<br/>
	 * [必传]APP_PRIVATE_KEY：开发者应用私钥，由开发者自己生成<br/>
	 * [必传]FORMAT：参数返回格式，只支持json<br/>
	 * [必传]CHARSET：请求和签名使用的字符编码格式，支持GBK和UTF-8<br/>
	 * [必传]ALIPAY_PUBLIC_KEY：支付宝公钥，由支付宝生成<br/>
	 * [必传]SIGN_TYPE：商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2<br/>
	 * [非必传]body:对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。<br/>
	 * [必传]subject：商品的标题/交易标题/订单标题/订单关键字等。<br/>
	 * [必传]out_trade_no：商户网站唯一订单号	<br/>
	 * [必传]timeout_express：该笔订单允许的最晚付款时间，逾期将关闭交易。
	 * 						     取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 
	 * 						     该参数数值不接受小数点， 如 1.5h，可转换为 90m。注：若为空，则默认为15d。<br/>
	 * [必传]total_amount：订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]<br/>
	 * [必传]product_code：销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY<br/>
	 * @return
	 * @author huchenghao
	 * @throws AlipayApiException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String createAliPayStr(Map<String, String> aliPayMap)
			throws Exception{
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				aliPayMap.get("app_id"), 
				aliPayMap.get("app_private_key"),
				"json", "utf-8", aliPayMap.get("alipay_public_key"), "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(aliPayMap.get("body"));
		model.setSubject(aliPayMap.get("subject"));
		model.setOutTradeNo(aliPayMap.get("out_trade_no"));// 请保证OutTradeNo值每次保证唯一
		model.setTimeoutExpress("");
		model.setTotalAmount(aliPayMap.get("total_fee"));
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(aliPayMap.get("notify_url"));
		try {
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);// 这里和普通的接口调用不同，使用的是sdkExecute
			return response.getBody();
		} catch (AlipayApiException e) {
			logger.error("createAliPayStr-error,out_trade_no:["+aliPayMap.get("out_trade_no")+"]");
			e.printStackTrace();
			return "error";
		}
	}
	/** 
	* @Title: refundAliPayStr 
	* @Description: 退款
	* @param aliPayMap 
	* @author 
	* @date 2018年12月14日
	* @return void
	 * @throws AlipayApiException 
	*/
	public static String refundAliPayStr(Map<String, String> aliPayMap) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				aliPayMap.get("app_id"),
				aliPayMap.get("app_private_key"),
				"json", "utf-8",aliPayMap.get("alipay_rsa_check_public_key"),"RSA2");
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
		"\"out_trade_no\":\""+aliPayMap.get("out_trade_no")+"\"," +
		"\"trade_no\":\"\"," +
		"\"refund_amount\":"+new BigDecimal(aliPayMap.get("refund_amount"))+"," +
		"\"refund_currency\":\"\"," +
		"\"refund_reason\":\""+aliPayMap.get("refund_reason")+"\"," +
		"\"out_request_no\":\""+aliPayMap.get("out_request_no")+"\"}");
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		System.out.println("ALiPayCore:::::::response.getParams()===="+response.getParams());
		if(response.isSuccess()){
			System.out.println("REFUND:SUCCESS");
			return "SUCCESS";
		} else {
			System.out.println("REFUND:FAIL ");
			return "FAIL";
		}
		
	}
	/** 
	* @Title: tradeQuery 
	* @Description: 查询订单
	* @return 
	* @author 大都督
	* @date 2018年12月18日
	* @return String
	 * @throws Exception 
	*/
	public static JSONObject tradeQuery(Map<String, String> aliPayMap) throws Exception {
		AlipayClient alipayClient = new DefaultAlipayClient(
				"https://openapi.alipay.com/gateway.do",
				aliPayMap.get("app_id"),
				aliPayMap.get("app_private_key"),
				"json","utf-8",aliPayMap.get("alipay_rsa_check_public_key"),"RSA2");
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" +
		"\"out_trade_no\":\""+aliPayMap.get("out_trade_no")+"\"," +
		"\"trade_no\":\"\"," +
		"\"org_pid\":\"\"" +
		"  }");
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		return JSONObject.parseObject(response.getBody());
	}
	/**
	 * 
	 * @Title: checkAliPayParam
	 * @Description: JAVA服务端验证异步通知信息参数以及返回参数
	 * @param request
	 * @param aliPayMap
	 * @return
	 * @author huchenghao
	 * @throws AlipayApiException 
	 */
	public static JSONObject checkAliPayParam(HttpServletRequest request,Map<String,String> aliPayMap) throws AlipayApiException{
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		boolean flag = AlipaySignature.rsaCheckV1(params, aliPayMap.get("alipay_rsa_check_public_key"), "utf-8","RSA2");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", flag);
		jsonObject.put("params", params);
		return jsonObject;
	}
	
	
	
}
