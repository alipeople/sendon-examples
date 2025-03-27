package io.sendon.example.kakao.channel;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.response.RequestAuthToken;

public class KakaoRequestVerification extends BaseScenario {

    @Override
    public void execute() {
      RequestAuthToken requestAuthToken = sendon.kakao.requestAuthToken(KKO_SEND_PROFILE_ID, KKO_CHANNEL_PHONE_NUMBER);
      Log.d("RequestAuthToken: " + gson.toJson(requestAuthToken));
    }


    @Override
    public String getDescription() {
        return "[카카오] 채널 인증번호 요청";
    }

}
