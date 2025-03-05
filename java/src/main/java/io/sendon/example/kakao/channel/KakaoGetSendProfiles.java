package io.sendon.example.kakao.channel;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.GetSendProfilesResponseSchema;

public class KakaoGetSendProfiles extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      GetSendProfilesResponseSchema response = sendon.getKakao().getSendProfiles();
      System.out.println("응답:\n\t" + response.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 발신프로필 리스트 조회";
  }

}