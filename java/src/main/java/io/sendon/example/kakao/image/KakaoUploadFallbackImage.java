package io.sendon.example.kakao.image;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.UploadFallbackImageResponseSchema;

public class KakaoUploadFallbackImage extends BaseScenario{

    @Override
    public void execute() throws InterruptedException {
        try {
          List<Object> files = Arrays.asList(new File("./img/aligo.png"));
          UploadFallbackImageResponseSchema response = sendon.getKakao().uploadFallbackImage(files);
          System.out.println("응답:\n\t" + response.toJson());

        } catch (ApiException e) {
          handleException(e);
        }
    }

    @Override
    public String getDescription() {
        return "[카카오] 대체문자 이미지 업로드";
    }
  
}
