package io.sendon.example.kakao.image;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.response.UploadFriendtalkImage;

public class KakaoUploadFriendImage extends BaseScenario{

    @Override
    public void execute() {
      File file = new File("./img/aligo.png");
      UploadFriendtalkImage uploadFriendtalkImage = sendon.kakao.uploadFriendtalkImage(file);
      Log.d("UploadFriendtalkImage: " + gson.toJson(uploadFriendtalkImage));
    }

    @Override
    public String getDescription() {
        return "[카카오] 친구톡 이미지 업로드";
    }

}
