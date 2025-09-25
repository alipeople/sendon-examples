package io.sendon.example.sms.shortmessage;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.sms.request.SmsBuilder;
import io.sendon.sms.response.SendSms;

public class SmsSendShortMessageNowScenario extends BaseScenario {

  @Override
  public void execute() {
    SendSms sendSms = sendon.sms.sendSms(new SmsBuilder()
        .setFrom(SMS_MOBILE_FROM)
        .setTo(Arrays.asList(SMS_MOBILE_TO))
        .setMessage("Hello, World!")
        .setIsAd(false)
    );
    Log.d("응답: " + gson.toJson(sendSms));
  }

  @Override
  public String getDescription() {
    return "[SMS] 즉시문자 발송";
  }
}