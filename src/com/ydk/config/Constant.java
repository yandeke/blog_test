package com.ydk.config;

/**
 * Created by lujianing on 2014-6-9.
 */
public class Constant {
    public final static String UTF_8="UTF-8";

    public final static String APPID="";
    public final static String APP_SECRET="";
    
    public final static String EMAIL_USERNAME=""; //发送源
    public final static String EMAIL_PASSWORD="";//密码
    public final static String EMAIL_HOST="smtp.126.com";//邮件服务器
    public final static String EMAIL_PORT="25";
    
    public final static String EMAIL_CONTENT_START="您好！您正在注册didiu网，为保障您帐号的安全性，请点击一下链接继续完成注册，链接有效期为30分钟，并将在点击一次后失效！";//文件内容
    
    public final static String EMAIL_SUBJECT="Blog注册验证";//文件标题
    
    public final static int MIN_LENGTH=6;
    
    public final static int MAX_LENGTH=20;
    
    public final static String ERROR_MES="信息有误";
    
    public final static String REGISTER_SUCCESS="请登录您的邮箱继续完成注册";
    
    public final static String SMS_UID="";
    
    public final static String SMS_KEY="";
    
    public final static String CONTENT_NAME="Content-Type";
    
    public final static String CONTENT_TYPE_VALUE="application/x-www-form-urlencoded;charset=gbk";
    
    public final static String SMS_URL="http://gbk.sms.webchinese.cn";
    
    public final static String SMS_TEXT_HEAD="验证码：";
    
    public final static String SMS_TEXT_TAIL="【全球科技软件公司】";
    
}
