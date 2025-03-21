package io.sendon.example.sms.shortmessage;

import java.time.OffsetDateTime;
import java.util.Collections;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.Reservation;
import io.sendon.model.SendMessageRequestDto;
import io.sendon.model.SendMessageRequestDtoToInner;
import io.sendon.model.SendMessageResponseDto;
import io.sendon.model.SmsMessageType;

public class SmsSendShortMessageScheduleScenario extends BaseScenario {

  @Override
  public void execute() {
    try {
      SendMessageRequestDto request = createRequest();
      SendMessageResponseDto response = sendon.getSms().send(request);
      System.out.println("응답:\n\t" + response.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  private SendMessageRequestDto createRequest() {
    SendMessageRequestDto dto = new SendMessageRequestDto();
    dto.setType(SmsMessageType.SMS);
    dto.setFrom(SMS_MOBILE_FROM);
    dto.setTo(Collections.singletonList(new SendMessageRequestDtoToInner(SMS_MOBILE_TO)));

    OffsetDateTime reservationTime = OffsetDateTime.now().plusMinutes(3);
    Reservation reservation = new Reservation().datetime(reservationTime);
    dto.setReservation(reservation);
    dto.setMessage("안녕하세요. Sendon SDK Java를 이용한 테스트 예약메시지 입니다.(" + generateRandomCode() + ")");
    return dto;
  }

  @Override
  public String getDescription() {
    return "[SMS] 예약문자 발송";
  }
}