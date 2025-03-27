package io.sendon.example.sms.longmessage;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.sms.response.SendSms;

public class SmsSendLongMessageNowScenario extends BaseScenario {

  @Override
  public void execute() {
    SendSms sendSms = sendon.sms.sendLms(SMS_MOBILE_FROM, Arrays.asList(SMS_MOBILE_TO), "Title", "Hello, World!", true, null);
    Log.d("SendSms: " + gson.toJson(sendSms));
  }

  @Override
  public String getDescription() {
    return "[LMS] 즉시문자 발송";
  }
}