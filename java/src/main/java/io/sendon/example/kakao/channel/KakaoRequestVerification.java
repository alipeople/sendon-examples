package io.sendon.example.kakao.channel;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.RequestAuthTokenRequestSchema;
import io.sendon.model.RequestAuthTokenResponseSchema;

public class KakaoRequestVerification extends BaseScenario {

    @Override
    public void execute() throws InterruptedException {
        try {
          RequestAuthTokenRequestSchema request = createRequest();
          RequestAuthTokenResponseSchema response = sendon.getKakao().requestAuthToken(request);
          System.out.println("응답:\n\t" + response.toJson());

        } catch (ApiException e) {
          handleException(e);
        }
    }

    private RequestAuthTokenRequestSchema createRequest() {
      RequestAuthTokenRequestSchema dto = new RequestAuthTokenRequestSchema();
      dto.setChannelId(KKO_CHANNEL_ID);
      dto.setPhoneNumber(KKO_CHANNEL_PHONE_NUMBER);
      return dto;
    }

    @Override
    public String getDescription() {
        return "[카카오] 채널 인증번호 요청";
    }
  
}
