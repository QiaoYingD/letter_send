package com.example.letter_send.utlis;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.example.letter_send.config.AlyVerificationConfig;

import java.util.Random;

public class AlyLetter {


    public static SendSmsResponse sendSms(String phoneNumbers, String code) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("南京", AlyVerificationConfig.ALY_ACCESSKEYID, AlyVerificationConfig.ALY_ACCESSLEYSECRET);
        DefaultProfile.addEndpoint("南京", "南京", AlyVerificationConfig.ALY_PRODUCT, AlyVerificationConfig.ALY_DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档内容
        SendSmsRequest request = new SendSmsRequest();
        //必填：待发送手机号
        request.setPhoneNumbers(phoneNumbers);
        //必填：短信签名-可在短信控制台中找到
        request.setSignName("集翠");
        //必填：短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_169641323");
        //可选：模板中的变量替换Json串，如模板内容为：您正在申请手机注册，验证码为：${code}，5分钟内有效！
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     * 随机生成6位随机数
     */
    public static String randomCode() {
        return ((int) ((Math.random() * 9 + 1) * 100000)) + "";
    }

}
