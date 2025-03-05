package io.sendon.example.kakao.channel;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.RegisterChannelRequestSchema;
import io.sendon.model.RegisterChannelResponseSchema;

public class KakaoRegisterChannel extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      RegisterChannelRequestSchema request = createRequest();
      RegisterChannelResponseSchema response = sendon.getKakao().registerChannel(request);
      System.out.println("응답:\n\t" + response.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  private RegisterChannelRequestSchema createRequest() {
    RegisterChannelRequestSchema dto = new RegisterChannelRequestSchema();
    dto.setChannelId(KKO_CHANNEL_ID);
    dto.setPhoneNumber(KKO_CHANNEL_PHONE_NUMBER);
    dto.setToken(KKO_TOKEN_YOU_RECEIVED);
    return dto;
  }

  @Override
  public String getDescription() {
    return "[카카오] 채널 등록";
  }

}