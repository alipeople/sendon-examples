package io.sendon.example.kakao.message;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.request.FriendtalkBuilder;
import io.sendon.kakao.response.SendFriendtalk;

/**
 * 친구톡 발송 (deprecated)
 */
public class KakaoSendFriendTalk extends BaseScenario {

    @Override
    public void execute() throws InterruptedException {
      SendFriendtalk friendtalk = sendon.kakao.sendFriendtalk(new FriendtalkBuilder()
          .setProfileId(KKO_CHANNEL_ID)
          .setTemplateId(KKO_TEMPLATE_ID)
          .setTo(Arrays.asList(KKO_MOBILE_TO))
          .setMessage("Hello, World!")
          .setIsAd(true)
      );
      Log.d("SendFriendtalk: " + gson.toJson(friendtalk));
    }

    @Override
    public String getDescription() {
        return "[카카오] 친구톡 발송";
    }

}
