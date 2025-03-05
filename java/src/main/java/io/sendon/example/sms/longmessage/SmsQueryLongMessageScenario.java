package io.sendon.example.sms.longmessage;

import java.util.Arrays;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.GetMessageResponseDto;
import io.sendon.model.SendMessageRequestDto;
import io.sendon.model.SendMessageRequestDtoToInner;
import io.sendon.model.SendMessageResponseDto;
import io.sendon.model.SmsMessageType;

public class SmsQueryLongMessageScenario extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      SendMessageRequestDto dto = new SendMessageRequestDto();
      dto.setType(SmsMessageType.LMS);
      dto.setFrom(SMS_MOBILE_FROM);

      SendMessageRequestDtoToInner inner = new SendMessageRequestDtoToInner(SMS_MOBILE_TO);
      dto.setTo(Arrays.asList(inner));
      dto.title(getDescription());
      dto.setMessage("안녕하세요. Sendon SDK Java를 이용한 테스트 메시지 입니다.(" + generateRandomCode() + ")");
      SendMessageResponseDto sendMessageResponse = sendon.getSms().send(dto);
      System.out.println("응답:\n\t" + sendMessageResponse.toJson());
      
      sleep(5000);

      String groupId = sendMessageResponse.getData().getGroupId();
      GetMessageResponseDto getMessageResponse = sendon.getSms().find(groupId);
      System.out.println("응답:\n\t" + getMessageResponse.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[LMS] 발송문자 조회";
  }
}