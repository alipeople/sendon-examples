package io.sendon.example.sms.longmessage;

import java.util.Collections;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.SendMessageRequestDto;
import io.sendon.model.SendMessageRequestDtoToInner;
import io.sendon.model.SendMessageResponseDto;
import io.sendon.model.SmsMessageType;

public class SmsSendLongMessageNowScenario extends BaseScenario {

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
    dto.setType(SmsMessageType.LMS);
    dto.setFrom(SMS_MOBILE_FROM);
    dto.setTo(Collections.singletonList(new SendMessageRequestDtoToInner(SMS_MOBILE_TO)));
    dto.title(getDescription());
    dto.setMessage("안녕하세요. Sendon SDK Java를 이용한 테스트 메시지 입니다.(" + generateRandomCode() + ")");
    return dto;
  }

  @Override
  public String getDescription() {
    return "[LMS] 즉시문자 발송";
  }
}