package com.jc.istore.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

/**
 * 
 * 描述：短信发送
 * 
 * @author zhangli 2016年11月1日 下午4:08:14
 * @version 1.0
 */
public class SmsUtil {

    static Log logger = Log4jUtils.getLog(SmsUtil.class); // 短信的发送状态日志



    /**
     * 
     * 描述：发送短信
     * 
     * @param phone
     * @param action
     * @author zhangli 2016年11月2日 上午9:30:53
     * @version 1.0
     */
    public static void sendSms(String url, String account, String pwd, String content, String phone, String sendTime) {
        Map<String, String> smsParams = new HashMap<>();
        String sendSmsState = null; // 发送短信的返回的状态
        smsParams.put("CorpID", account); // 第三方短信平台账号
        smsParams.put("Pwd", pwd); // 第三方密码
        try {
            content = URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        smsParams.put("Content", content); // 短信发送的内容
        smsParams.put("Mobile", phone); // 接受短信的手机号
        smsParams.put("SendTime", sendTime); // 定时发送短信的时间，固定14位长度字符串，比如：20060912152435代表2006年9月12日15时24分35秒，为空表示立即发送
        try {
            sendSmsState = HttpUtil.get(url, smsParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int smsStateNum = Integer.parseInt(sendSmsState);
        switch (smsStateNum) {
        case -1:
            logger.info("账号未注册:" + sendSmsState);
            break;
        case -2:
            logger.info("其他错误:" + sendSmsState);
            break;
        case -3:
            logger.info("帐号或密码错误:" + sendSmsState);
            break;
        case -5:
            logger.info("余额不足，请充值:" + sendSmsState);
            break;
        case -6:
            logger.info("定时发送时间不是有效的时间格式:" + sendSmsState);
            break;
        case -7:
            logger.info("提交信息末尾未签名，请添加中文的企业签名【 】:" + sendSmsState);
            break;
        case -8:
            logger.info("发送内容需在1到300字之间:" + sendSmsState);
            break;
        case -9:
            logger.info("发送号码为空:" + sendSmsState);
            break;
        case -10:
            logger.info("定时时间不能小于系统当前时间:" + sendSmsState);
            break;
        case -100:
            logger.info("IP黑名单:" + sendSmsState);
            break;
        case -102:
            logger.info("账号黑名单:" + sendSmsState);
            break;
        case -103:
            logger.info("IP未导白:" + sendSmsState);
            break;
        default:
            logger.info("提交成功:" + sendSmsState);
        }
    }

}
