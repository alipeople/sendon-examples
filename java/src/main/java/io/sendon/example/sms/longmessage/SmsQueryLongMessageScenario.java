package io.sendon.example.sms.longmessage;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.sms.response.GetGroup;
import io.sendon.sms.response.SendSms;

public class SmsQueryLongMessageScenario extends BaseScenario {

  @Override
  public void execute() {
    SendSms sendSms = sendon.sms.sendLms(SMS_MOBILE_FROM, Arrays.asList(SMS_MOBILE_TO), "Title", "Hello, World!", true, null);
    Log.d("SendSms: " + gson.toJson(sendSms));

    sleep(5000);

    GetGroup getGroup = sendon.sms.getGroup(sendSms.data.groupId);
    Log.d("GetGroup: " + gson.toJson(getGroup));
  }

  @Override
  public String getDescription() {
    return "[LMS] 발송문자 조회";
  }
}