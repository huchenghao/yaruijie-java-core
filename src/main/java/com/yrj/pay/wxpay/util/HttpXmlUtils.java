package com.yrj.pay.wxpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import javax.net.ssl.HttpsURLConnection;

public class HttpXmlUtils {


    /**
     * 开始post提交参数到接口
     * 并接受返回
     * @param url
     * @param xml
     * @param method
     * @param contentType
     * @return
     */
    public static String xmlHttpProxy(String url,String xml,String method,String contentType){
        InputStream is = null;
        OutputStreamWriter os = null;

        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setDoInput(true);   
            conn.setDoOutput(true);   
            conn.setRequestProperty("Content-type", "text/xml");
            conn.setRequestProperty("Pragma:", "no-cache");  
            conn.setRequestProperty("Cache-Control", "no-cache");  
            conn.setRequestMethod("POST");
            os = new OutputStreamWriter(conn.getOutputStream());
            os.write(new String(xml.getBytes(contentType)));
            os.flush();

            //返回值
            is = conn.getInputStream();
            return getContent(is, "utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(os!=null){os.close();}
                if(is!=null){is.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解析返回的值
     * @param is
     * @param charset
     * @return
     */
    public static String getContent(InputStream is, String charset) {
        String pageString = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            isr = new InputStreamReader(is, charset);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            pageString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
                if(isr!=null){
                    isr.close();
                }
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb = null;
        }
        return pageString;
    }

    /**
     * 构造xml参数
     * @param xml
     * @return
     */
    public static String xmlInfo(Unifiedorder unifiedorder){
        if(unifiedorder!=null){
            StringBuffer bf = new StringBuffer();
            bf.append("<xml>");

            bf.append("<appid><![CDATA[");
            bf.append(unifiedorder.getAppid());
            bf.append("]]></appid>");

            bf.append("<mch_id><![CDATA[");
            bf.append(unifiedorder.getMch_id());
            bf.append("]]></mch_id>");

            bf.append("<nonce_str><![CDATA[");
            bf.append(unifiedorder.getNonce_str());
            bf.append("]]></nonce_str>");

            bf.append("<sign><![CDATA[");
            bf.append(unifiedorder.getSign());
            bf.append("]]></sign>");

            bf.append("<body><![CDATA[");
            bf.append(unifiedorder.getBody());
            bf.append("]]></body>");

            bf.append("<detail><![CDATA[");
            bf.append(unifiedorder.getDetail());
            bf.append("]]></detail>");

            bf.append("<attach><![CDATA[");
            bf.append(unifiedorder.getAttach());
            bf.append("]]></attach>");

            bf.append("<out_trade_no><![CDATA[");
            bf.append(unifiedorder.getOut_trade_no());
            bf.append("]]></out_trade_no>");

            bf.append("<total_fee><![CDATA[");
            bf.append(unifiedorder.getTotal_fee());
            bf.append("]]></total_fee>");

            bf.append("<spbill_create_ip><![CDATA[");
            bf.append(unifiedorder.getSpbill_create_ip());
            bf.append("]]></spbill_create_ip>");

            bf.append("<time_start><![CDATA[");
            bf.append(unifiedorder.getTime_start());
            bf.append("]]></time_start>");

            bf.append("<time_expire><![CDATA[");
            bf.append(unifiedorder.getTime_expire());
            bf.append("]]></time_expire>");

            bf.append("<notify_url><![CDATA[");
            bf.append(unifiedorder.getNotify_url());
            bf.append("]]></notify_url>");

            bf.append("<trade_type><![CDATA[");
            bf.append(unifiedorder.getTrade_type());
            bf.append("]]></trade_type>");

            bf.append("</xml>");
            return bf.toString();
        }

        return "";
    }

    /**
     * 
    * @Title: xmlInfo 
    * @Description: 针对微信订单查询，构造xml请求参数
    * @param unifiedorderQuery
    * @return 
    * @author 大都督
    * @date 2018年12月18日
    * @return String
     */
    public static String xmlInfo(UnifiedorderQuery unifiedorderQuery){
        if(unifiedorderQuery!=null){
            StringBuffer bf = new StringBuffer();
            bf.append("<xml>");

            bf.append("<appid><![CDATA[");
            bf.append(unifiedorderQuery.getAppid());
            bf.append("]]></appid>");

            bf.append("<mch_id><![CDATA[");
            bf.append(unifiedorderQuery.getMch_id());
            bf.append("]]></mch_id>");

            bf.append("<nonce_str><![CDATA[");
            bf.append(unifiedorderQuery.getNonce_str());
            bf.append("]]></nonce_str>");

            bf.append("<sign><![CDATA[");
            bf.append(unifiedorderQuery.getSign());
            bf.append("]]></sign>");


            bf.append("<out_trade_no><![CDATA[");
            bf.append(unifiedorderQuery.getOut_trade_no());
            bf.append("]]></out_trade_no>");

            bf.append("</xml>");
            return bf.toString();
        }

        return "";
    }
    

    /**
     * post请求并得到返回结果
     * @param requestUrl
     * @param requestMethod
     * @param output
     * @return
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String output) {
        try{
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);
            if (null != output) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return buffer.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return "";
    }

    public static String getRequestXml(SortedMap<Object,Object> parameters){
  	  StringBuffer sb = new StringBuffer();
  	  sb.append("<xml>");
  	  Set es = parameters.entrySet();
  	  Iterator it = es.iterator();
  	  while(it.hasNext()) {
  	      Map.Entry entry = (Map.Entry)it.next();
  	      String k = (String)entry.getKey();
  	      String v = (String)entry.getValue();
  	      if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
  	          sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
  	      }else {
  	          sb.append("<"+k+">"+v+"</"+k+">");
  	      }
  	  }
  	  sb.append("</xml>");
  	  return sb.toString();
  	}
}
