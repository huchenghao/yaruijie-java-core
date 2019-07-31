package com.yrj.pay.wxpay.util;

/**
 * 
 * @Description 微信订单查询实体封装类
 * @author 大都督
 * @date 2018年12月18日
 */
public class UnifiedorderQuery {
	private String appid;//微信分配的公众账号ID（企业号corpid即为此appId）,例如：wxd678efh567hg6787
	private String mch_id;//商户id
	private String nonce_str;//随机字符串:数字+大写字母的组合，32位
	private String sign;//签名
	private String out_trade_no;//商户系统内部的订单号
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
		


	
}
