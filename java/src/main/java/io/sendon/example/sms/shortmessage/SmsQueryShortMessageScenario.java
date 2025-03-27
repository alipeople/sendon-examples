package io.sendon.example.sms.shortmessage;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.sms.response.GetGroup;
import io.sendon.sms.response.SendSms;

public class SmsQueryShortMessageScenario extends BaseScenario {

  @Override
  public void execute() {
    SendSms sendSms = sendon.sms.sendSms(SMS_MOBILE_FROM, Arrays.asList(SMS_MOBILE_TO), "Hello, World!", true, null);
    Log.d("SendSms: " + gson.toJson(sendSms));

    sleep(5000);

    GetGroup getGroup = sendon.sms.getGroup(sendSms.data.groupId);
    Log.d("GetGroup: " + gson.toJson(getGroup));
  }

  @Override
  public String getDescription() {
    return "[SMS] 발송문자 조회";
  }
}