package io.sendon.example.sms.longmessage;

import java.time.OffsetDateTime;
import java.util.Collections;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.CancelResponseDto;
import io.sendon.model.Reservation;
import io.sendon.model.SendMessageRequestDto;
import io.sendon.model.SendMessageRequestDtoToInner;
import io.sendon.model.SendMessageResponseDto;
import io.sendon.model.SmsMessageType;

public class SmsCancelLongMessageScenario extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      SendMessageRequestDto request = createRequest();
      SendMessageResponseDto response = sendon.getSms().send(request);
      System.out.println("응답:\n\t" + response.toJson());

      sleep(5000);
      
      CancelResponseDto cancelResponseDto = sendon.getSms().cancelGroup(response.getData().getGroupId());
      System.out.println("응답:\n\t" + cancelResponseDto.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  private SendMessageRequestDto createRequest() {
    SendMessageRequestDto dto = new SendMessageRequestDto();
    dto.setType(SmsMessageType.LMS);
    dto.setFrom(SMS_MOBILE_FROM);
    dto.setTo(Collections.singletonList(new SendMessageRequestDtoToInner(SMS_MOBILE_TO)));

    OffsetDateTime reservationTime = OffsetDateTime.now().plusMinutes(60*3);
    Reservation reservation = new Reservation().datetime(reservationTime);
    dto.setReservation(reservation);
    dto.title(getDescription());
    dto.setMessage("안녕하세요. Sendon SDK Java를 이용한 테스트 예약메시지 입니다.(" + generateRandomCode() + ")");
    return dto;
  }

  @Override
  public String getDescription() {
    return "[LMS] 예약문자 취소";
  }
}