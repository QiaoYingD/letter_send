package com.example.letter_send.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

public class AlyVerificationConfig {

    /**产品名称:云通信短信API产品,开发者无需替换*/
    public static final String ALY_PRODUCT = "Dysmsapi";

    /**产品域名,开发者无需替换*/
    public static final String ALY_DOMAIN = "dysmsapi.aliyuncs.com";

    /** 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) 配置文件中跟换成自己的AK*/
    public static String ALY_ACCESSKEYID;

    /** 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) 配置文件中跟换成自己的AK*/
    public static String ALY_ACCESSLEYSECRET;


    static {
        try {
            String path = URLDecoder.decode(AlyVerificationConfig.class.getResource("/Aly.properties").getPath(), "utf-8");
            Properties properties = new Properties();
            //使用InputStream流读取properties文件
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            properties.load(bufferedReader);
            //读取值赋给全局变量
            ALY_ACCESSKEYID = properties.getProperty("aly.accessKeyId");
            ALY_ACCESSLEYSECRET = properties.getProperty("aly.accessKeySecret");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
