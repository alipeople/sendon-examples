package io.sendon.example.kakao.message;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.request.AlimtalkBuilder;
import io.sendon.kakao.response.SendAlimtalk;

public class KakaoSendAlimTalk extends BaseScenario {

    @Override
    public void execute() throws InterruptedException {
      SendAlimtalk sendAlimtalkResult = sendon.kakao.sendAlimtalk(new AlimtalkBuilder()
          .setProfileId(KKO_SEND_PROFILE_ID)
          .setTemplateId(KKO_TEMPLATE_ID)
          .setTo(Arrays.asList(KKO_MOBILE_TO))
      );
      Log.d("SendAlimtalk: " + gson.toJson(sendAlimtalkResult));
    }


    @Override
    public String getDescription() {
        return "[카카오] 알림톡 발송";
    }

}
