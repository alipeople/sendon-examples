package io.sendon.example.kakao.channel;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.request.Profile;
import io.sendon.kakao.response.RegisterProfile;

public class KakaoRegisterProfile extends BaseScenario {

  @Override
  public void execute() {
    RegisterProfile registerProfile = sendon.kakao.registerProfile(
      new Profile()
          .setToken(KKO_TOKEN_YOU_RECEIVED)
          .setPhoneNumber(KKO_CHANNEL_PHONE_NUMBER)
          .setChannelId(KKO_CHANNEL_ID)
    );
    Log.d("RegisterProfile: " + gson.toJson(registerProfile));
  }


  @Override
  public String getDescription() {
    return "[카카오] 채널 등록";
  }

}