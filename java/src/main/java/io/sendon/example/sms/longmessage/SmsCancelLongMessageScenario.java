package io.sendon.example.sms.longmessage;

import java.time.OffsetDateTime;
import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.sms.request.Reservation;
import io.sendon.sms.response.CancelGroup;
import io.sendon.sms.response.SendSms;
public class SmsCancelLongMessageScenario extends BaseScenario {

  @Override
  public void execute() {
    OffsetDateTime reservationTime = OffsetDateTime.now().plusMinutes(60);
    Reservation reservation = new Reservation(reservationTime.toString());
    SendSms sendSms = sendon.sms.sendLms(SMS_MOBILE_FROM, Arrays.asList(SMS_MOBILE_TO), "Title", "Hello, World!", true, reservation);
    Log.d("SendSms: " + gson.toJson(sendSms));

    sleep(5000);

    CancelGroup cancelGroup = sendon.sms.cancelGroup(sendSms.data.groupId);
    Log.d("CancelGroup: " + gson.toJson(cancelGroup));

  }

  @Override
  public String getDescription() {
    return "[LMS] 예약문자 취소";
  }
}