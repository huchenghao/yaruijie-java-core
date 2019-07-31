package com.yrj.pay.wxpay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.yrj.pay.wxpay.util.HttpXmlUtils;
import com.yrj.pay.wxpay.util.ParseXMLUtils;
import com.yrj.pay.wxpay.util.RandCharsUtils;
import com.yrj.pay.wxpay.util.Unifiedorder;
import com.yrj.pay.wxpay.util.UnifiedorderQuery;
import com.yrj.pay.wxpay.util.WXSignUtils;



public class WxPayCore {
	
	/**
	 * @Title: sign
	 * @Description: APP支付统一下单
	 * @param 
	 * [必传]appid:微信开放平台审核通过的应用APPID（请登录open.weixin.qq.com查看，注意与公众号的APPID不同）<br/>
	 * [必传]mch_id:微信支付分配的商户号<br/>
	 * [非必传]device_info:终端设备号(门店号或收银设备ID)，默认请传"WEB"<br/>
	 * [必传]nonce_str:随机字符串，不长于32位。推荐随机数生成算法<br/>
	 * [必传]sign：签名(具体签名值代码中实现了)<br/>
	 * [非必传]sign_type：签名类型，目前支持HMAC-SHA256和MD5，默认为MD5<br/>
	 * [必传]body：商品描述，商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。<br/>
	 * [非必传]detail：商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”<br/>
	 * [非必传]attach：附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据<br/>
	 * [必传]out_trade_no：商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号<br/>
	 * [非必传]fee_type：符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型<br/>
	 * [必传]total_fee：订单总金额，单位为分<br/>
	 * [必传]spbill_create_ip：用户端实际ip<br/>
	 * [非必传]time_start：订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010<br/>
	 * [非必传]time_expire：订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
	 * 					         订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
	 * 					         所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。建议：最短失效时间间隔大于1分<br/>
	 * [非必传]goods_tag：订单优惠标记，代金券或立减优惠功能的参数<br/>
	 * [必传]notify_url：接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。<br/>
	 * [必传]trade_type:交易类型	,APP<br/>
	 * [非必传]limit_pay:指定支付方式	.no_credit--指定不能使用信用卡支付<br/>
	 * [非必传]receipt:开发票入口开放标识.Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效<br/>
	 * @return
	 * @author huchenghao
	 * @date: 2018年12月7日 下午5:30:21
	 */
	public static String sign(Map<String, String> wxPayMap){
		String nonce_str = RandCharsUtils.getRandomString(32);
		String time_start = RandCharsUtils.timeStart();
		String time_expire = RandCharsUtils.timeExpire();
		
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", wxPayMap.get("appid"));
        parameters.put("mch_id", wxPayMap.get("mch_id"));
        parameters.put("nonce_str", nonce_str);
        parameters.put("body", wxPayMap.get("body"));
        parameters.put("detail", wxPayMap.get("detail"));
        parameters.put("attach", wxPayMap.get("attach"));
        parameters.put("out_trade_no", wxPayMap.get("out_trade_no"));
        parameters.put("total_fee", wxPayMap.get("total_fee"));
        parameters.put("time_start", time_start);
        parameters.put("time_expire", time_expire);
        parameters.put("notify_url", wxPayMap.get("notify_url"));
        parameters.put("trade_type", "APP");
        parameters.put("spbill_create_ip", wxPayMap.get("spbill_create_ip"));
        String sign = WXSignUtils.createSign("UTF-8", parameters,wxPayMap.get("key"));
        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(wxPayMap.get("appid"));
        unifiedorder.setMch_id(wxPayMap.get("mch_id"));
        unifiedorder.setNonce_str(nonce_str);
        unifiedorder.setSign(sign);
        unifiedorder.setBody(wxPayMap.get("body"));
        unifiedorder.setDetail(wxPayMap.get("detail"));
        unifiedorder.setAttach(wxPayMap.get("attach"));
        unifiedorder.setOut_trade_no(wxPayMap.get("out_trade_no"));
        unifiedorder.setTotal_fee(Integer.parseInt(wxPayMap.get("total_fee")));
        unifiedorder.setSpbill_create_ip(wxPayMap.get("spbill_create_ip"));
        unifiedorder.setTime_start(time_start);
        unifiedorder.setTime_expire(time_expire);
        unifiedorder.setNotify_url(wxPayMap.get("notify_url"));
        unifiedorder.setTrade_type("APP");

        //构造xml参数
        String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
        String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String method = "POST";
        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
        String jsonStr =ParseXMLUtils.jdomParseXmlToStr(weixinPost);
        //二次签名
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);   //fromObject(json);
        String appid2 =(String) jsonObject.get("appid");
        String mch_id2 =(String) jsonObject.get("mch_id");
        String nonce_str2 =(String) jsonObject.get("nonce_str");
        String prepay_id2 =(String) jsonObject.get("prepay_id");//预支付交易会话标识	
        //参数：开始生成签名
        SortedMap<Object,Object> parameters2 = new TreeMap<Object,Object>();
        parameters2.put("appid", appid2);
        parameters2.put("partnerid", mch_id2);
        parameters2.put("noncestr", nonce_str2);
        parameters2.put("package", "Sign=WXPay");
        parameters2.put("prepayid", prepay_id2);
        parameters2.put("timestamp", System.currentTimeMillis()/1000);
        
        String sign_result = WXSignUtils.createSign("UTF-8", parameters2,wxPayMap.get("key"));
	    
        JSONObject re_json_str = JSONObject.parseObject(jsonStr);
        re_json_str.put("sign", sign_result);
        re_json_str.put("timestamp", System.currentTimeMillis()/1000);
        return re_json_str.toJSONString();
       
	}
	/** 
	* @Title: orderquery 
	* @Description: 查询订单
	* @return 
	* @author 大都督
	* @date 2018年12月18日
	* @return String
	 * @throws Exception 
	*/
	public static JSONObject orderquery(Map<String, String> wxPayMap) throws Exception {
		String nonce_str = RandCharsUtils.getRandomString(32);
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", wxPayMap.get("appid"));
        parameters.put("mch_id", wxPayMap.get("mch_id"));
        parameters.put("nonce_str", nonce_str);
        parameters.put("out_trade_no", wxPayMap.get("out_trade_no"));
        String sign = WXSignUtils.createSign("UTF-8", parameters,wxPayMap.get("key"));
        UnifiedorderQuery unifiedorderQuery = new UnifiedorderQuery();
        unifiedorderQuery.setAppid(wxPayMap.get("appid"));
        unifiedorderQuery.setMch_id(wxPayMap.get("mch_id"));
        unifiedorderQuery.setNonce_str(nonce_str);
        unifiedorderQuery.setSign(sign);
        unifiedorderQuery.setOut_trade_no(wxPayMap.get("out_trade_no"));
        //构造xml参数
        String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorderQuery);
        String wxUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
        String method = "POST";
        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
        String jsonStr =ParseXMLUtils.jdomParseXmlToStr(weixinPost);
        return JSONObject.parseObject(jsonStr);
	}
	/** 
	* @Title: refund 
	* @Description: 退款
	* @param wxPayMap
	* @return 
	* @author 大都督
	* @date 2018年12月14日
	* @return String
	 * @throws Exception 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	*/
	public static String refund(Map<String, String> wxPayMap) throws NoSuchAlgorithmException, CertificateException, Exception {
		   SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	       parameters.put("appid", wxPayMap.get("appid"));
	       parameters.put("mch_id", wxPayMap.get("mch_id"));
	       parameters.put("nonce_str", RandCharsUtils.getRandomString(32));
	      //在notify_url中解析微信返回的信息获取到 transaction_id，此项不是必填，详细请看上图文档
	      //parameters.put("transaction_id", "微信支付订单中调用统一接口后微信返回的 transaction_id");
	       parameters.put("out_trade_no", wxPayMap.get("out_trade_no"));
	       parameters.put("out_refund_no", wxPayMap.get("refundNo"));//我们自己设定的退款申请号，约束为UK
	       parameters.put("total_fee", wxPayMap.get("total_fee")) ; //单位为分
	       parameters.put("refund_fee",wxPayMap.get("refund_fee")); //单位为分
	       String sign =  WXSignUtils.createSign("UTF-8", parameters,wxPayMap.get("key"));
	       parameters.put("sign", sign);
	       String reuqestXml = HttpXmlUtils.getRequestXml(parameters);
	      
	       KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	       FileInputStream instream = new FileInputStream(new File(wxPayMap.get("apiclient_cert_url")));//放退款证书的路径
	       try {
	           keyStore.load(instream, wxPayMap.get("mch_id").toCharArray());
	       } finally {
	           instream.close();
	       }
			 SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, wxPayMap.get("mch_id").toCharArray()).build();
	       SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	               sslcontext,
	               new String[] { "TLSv1" },
	               null,
	               SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	       CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	       try {
	           HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");//退款接口
	           System.out.println("executing request" + httpPost.getRequestLine());
	           StringEntity  reqEntity  = new StringEntity(reuqestXml);
	           // 设置类型 
	           reqEntity.setContentType("application/x-www-form-urlencoded"); 
	           httpPost.setEntity(reqEntity);
	           CloseableHttpResponse response = httpclient.execute(httpPost);
	           try {
	               HttpEntity entity = response.getEntity();

	               System.out.println("----------------------------------------");
	               //System.out.println(response.getStatusLine());
	               if (entity != null) {
	              	 Map<String, String> map = new HashMap<String, String>();
	                   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
	                   SAXReader reader = new SAXReader();
	                   Document document = reader.read(bufferedReader);
	                   Element root = document.getRootElement();
	                   List<Element> elementList = root.elements();
	                   for (Element e : elementList) {
	    	                map.put(e.getName(), e.getText());
	    	             }
	                   if(map.get("return_code").equals("SUCCESS")){
	                	   return "SUCCESS";
	                   }else{
	                   		return "FAIL";
	                   }
	                   /*for (Map.Entry<String, String> entry : map.entrySet()) {  
	              	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
	              	} */
	               }
	               EntityUtils.consume(entity);
	           } finally {
	               response.close();
	           }
	       } finally {
	           httpclient.close();
	       }
	       return "";
		 }

}
