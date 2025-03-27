package io.sendon.example.sms.longmessage;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.sms.request.MmsBuilder;
import io.sendon.sms.request.Reservation;
import io.sendon.sms.response.SendSms;

public class SmsSendLongMessageScheduleScenario extends BaseScenario {

  @Override
  public void execute() {
    Reservation reservation = new Reservation("2080-03-21 00:00:00");
    SendSms sendSms = sendon.sms.sendMms(new MmsBuilder()
        .setFrom(SMS_MOBILE_FROM)
        .setTo(Arrays.asList(SMS_MOBILE_TO))
        .setTitle("Title")
        .setMessage("Hello, World!")
        .setReservation(reservation)
        .setIsAd(true)
    );
    Log.d("SendSms: " + gson.toJson(sendSms));

  }

  @Override
  public String getDescription() {
    return "[LMS] 예약문자 발송";
  }
}