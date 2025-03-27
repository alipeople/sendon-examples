package io.sendon.example.kakao.channel;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.response.GetProfiles;

public class KakaoGetSendProfiles extends BaseScenario {

  @Override
  public void execute() {
    GetProfiles getProfiles = sendon.kakao.getProfiles(2, null);
    Log.d("GetProfiles: " + gson.toJson(getProfiles));
  }

  @Override
  public String getDescription() {
    return "[카카오] 발신 프로필 조회";
  }
}