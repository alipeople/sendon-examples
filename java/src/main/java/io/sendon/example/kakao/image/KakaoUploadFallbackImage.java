package io.sendon.example.kakao.image;

import java.io.File;
import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.response.UploadFallbackImage;

public class KakaoUploadFallbackImage extends BaseScenario{

    @Override
    public void execute() {
      File file = new File("./img/aligo.png");
      UploadFallbackImage uploadFallbackImage = sendon.kakao.uploadFallbackImage(Arrays.asList(file));
      Log.d("UploadFallbackImage: " + gson.toJson(uploadFallbackImage));
    }

    @Override
    public String getDescription() {
        return "[카카오] 대체문자 이미지 업로드";
    }

}
