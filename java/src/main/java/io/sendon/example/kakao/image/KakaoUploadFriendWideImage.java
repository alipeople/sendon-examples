package io.sendon.example.kakao.image;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.response.UploadFriendtalkWideImage;

/**
 * 친구톡 와이드 이미지 업로드 (deprecated)
 */
public class KakaoUploadFriendWideImage extends BaseScenario{

    @Override
    public void execute() {
      File file = new File("./img/aligo.png");
      UploadFriendtalkWideImage uploadFriendtalkWideImage = sendon.kakao.uploadFriendtalkWideImage(file);
      Log.d("UploadFriendtalkWideImage: " + gson.toJson(uploadFriendtalkWideImage));
    }

    @Override
    public String getDescription() {
        return "[카카오] 친구톡 와이드 이미지 업로드";
    }

}
