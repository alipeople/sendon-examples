package io.sendon.example.kakao.brandmessage;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.response.BrandMessageImageUploadResponse;

/**
 * [카카오] 브랜드메시지 이미지 업로드 예제
 */
public class KakaoUploadBrandMessageImage extends BaseScenario {

  @Override
  public void execute() {
    try {
      File imageFile = new File("img" + File.separator + "sample-image.jpeg");

      if (!imageFile.exists()) {
        throw new IllegalStateException("샘플 이미지 파일을 찾을 수 없습니다: " + imageFile.getAbsolutePath());
      }

      BrandMessageImageUploadResponse response = sendon.kakao.uploadBrandMessageImage(imageFile);
      Log.d("브랜드메시지 이미지 업로드 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 이미지 업로드";
  }
}
