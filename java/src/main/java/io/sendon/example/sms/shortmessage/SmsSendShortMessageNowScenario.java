package io.sendon.example.sms.shortmessage;

import java.util.Arrays;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.SendMessageRequestDto;
import io.sendon.model.SendMessageRequestDtoToInner;
import io.sendon.model.SendMessageResponseDto;
import io.sendon.model.SmsMessageType;

public class SmsSendShortMessageNowScenario extends BaseScenario {

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
    dto.setTo(Arrays.asList(
      new SendMessageRequestDtoToInner("01900000001"),
      new SendMessageRequestDtoToInner("01900000002"),
      new SendMessageRequestDtoToInner("01900000003")
    ));
    dto.setMessage("안녕하세요. Sendon SDK Java를 이용한 테스트 메시지 입니다.(" + generateRandomCode() + ")");
    return dto;
  }

  @Override
  public String getDescription() {
    return "[SMS] 즉시문자 발송";
  }
}