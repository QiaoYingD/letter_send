package com.example.letter_send.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.letter_send.config.AlyVerificationConfig;
import com.example.letter_send.utlis.AlyLetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String index() {

        return "index";
    }

    @RequestMapping("sendSms")
    @ResponseBody
    public String sendSms() {
        String phoneNumbers="17633720773";
        String code = AlyLetter.randomCode();
        System.out.println(code);
        try {
            SendSmsResponse sendSmsResponse = AlyLetter.sendSms(phoneNumbers,code);
            System.out.println("短信接口返回的数据------------------");
            System.out.println("code=" + sendSmsResponse.getCode());
            System.out.println("message=" + sendSmsResponse.getMessage());
            System.out.println("requestId=" + sendSmsResponse.getRequestId());
            System.out.println("bizId=" + sendSmsResponse.getBizId());

            Thread.sleep(3000L);


        } catch (ClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return code;
    }
}
