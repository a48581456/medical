package common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import util.Util;



public class NimWeb {

    public static String registerWebUser(String accid, String name, String img) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://api.netease.im/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);

        String appKey = Constants.IM_APPKEY;
        String appSecret = Constants.IM_APPSECRET;
        String nonce =  Util.genRandomNum(5);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", accid));
        nvps.add(new BasicNameValuePair("name", name));
        if (!"".equals(img) && img != null) {
        	nvps.add(new BasicNameValuePair("icon", img));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        String tmpJs = EntityUtils.toString(response.getEntity(), "utf-8");
        
        JSONObject jsStr = JSONObject.fromObject(tmpJs);
        
        String token = "";
        
        String code = jsStr.getString("code");
        // 返回正确的时候
        if ("200".equals(code)) {
        	JSONObject jsInfo = jsStr.getJSONObject("info");
        	token = jsInfo.getString("token");
        }
        System.out.println(token);
        return token;
    }
    
    public static void updateUinfo(String accid, String name, String img) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://api.netease.im/nimserver/user/updateUinfo.action";
        HttpPost httpPost = new HttpPost(url);

        String appKey = Constants.IM_APPKEY;
        String appSecret = Constants.IM_APPSECRET;
        String nonce =  Util.genRandomNum(5);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", accid));
        if (!"".equals(name) && name != null) {
        	nvps.add(new BasicNameValuePair("name", name));
        }
        if (!"".equals(img) && img != null) {
        	nvps.add(new BasicNameValuePair("icon", img));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        
        String tmpJs = EntityUtils.toString(response.getEntity(), "utf-8");

        System.out.println(tmpJs);
    }
    
    public static void getUinfos(String accid, String name, String img) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://api.netease.im/nimserver/user/getUinfos.action";
        HttpPost httpPost = new HttpPost(url);

        String appKey = Constants.IM_APPKEY;
        String appSecret = Constants.IM_APPSECRET;
        String nonce =  Util.genRandomNum(5);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accids", "[\"zhouyz\"]"));
        if (!"".equals(name) && name != null) {
        	nvps.add(new BasicNameValuePair("name", name));
        }
        if (!"".equals(img) && img != null) {
        	nvps.add(new BasicNameValuePair("icon", img));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        
        String tmpJs = EntityUtils.toString(response.getEntity(), "utf-8");

        System.out.println(tmpJs);
    }
    
}
