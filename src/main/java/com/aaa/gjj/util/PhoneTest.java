package com.aaa.gjj.util;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * className:PhoneTest
 * discription:
 * author:qcm
 * createTime:2019-01-02 18:05
 */
public class PhoneTest {

    /**
     * 获取短信验证码方法
     * @param tel
     */
    public static int getModelMsg(String tel) {
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "b329b480eb2a4968b2db37d75bb4a359";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", tel);
        int codeNum = (int)((Math.random()*9+1)*100000);
        querys.put("param", "code:" + codeNum);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();
        //System.out.println(codeNum);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return codeNum;
    }

}
