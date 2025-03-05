package io.sendon.example.kakao.image;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.UploadFriendTalkImageResponseSchema;

public class KakaoUploadFriendImage extends BaseScenario{

    @Override
    public void execute() throws InterruptedException {
        try {
          List<Object> files = Arrays.asList(new File("./img/aligo.png"));
          UploadFriendTalkImageResponseSchema response = sendon.getKakao().uploadFriendTalkImage(files);
          System.out.println("응답:\n\t" + response.toJson());

        } catch (ApiException e) {
          handleException(e);
        }
    }

    @Override
    public String getDescription() {
        return "[카카오] 친구톡 이미지 업로드";
    }
  
}
