package io.sendon.example.kakao.brandmessage;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageButton;
import io.sendon.kakao.brandmessage.request.BrandMessageLinkType;
import io.sendon.kakao.brandmessage.request.BrandMessageWideTemplateRequest;
import io.sendon.kakao.brandmessage.response.BrandMessageImageUploadResponse;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateIdResponse;

/**
 * [카카오] 브랜드메시지 와이드형 템플릿 생성 예제
 */
public class KakaoCreateBrandMessageWideTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      File wideImageFile = new File("img" + File.separator + "sample-wide-image.jpeg");

      if (!wideImageFile.exists()) {
        throw new IllegalStateException("샘플 와이드 이미지 파일을 찾을 수 없습니다: "
            + wideImageFile.getAbsolutePath());
      }

      BrandMessageImageUploadResponse uploadResponse = sendon.kakao.uploadBrandMessageWideImage(wideImageFile);
      String wideImageUrl = uploadResponse != null ? uploadResponse.imageUrl : null;

      if (wideImageUrl == null || wideImageUrl.isBlank()) {
        Log.d("브랜드메시지 와이드 이미지 업로드에 실패했습니다.");
        return;
      }

      Log.d("업로드된 브랜드메시지 와이드 이미지 URL: " + wideImageUrl);

      BrandMessageWideTemplateRequest request = new BrandMessageWideTemplateRequest();
      request.setTemplateName("bm-wide-" + System.currentTimeMillis());
      request.setContent("#{고객명}님 맞춤 와이드 브랜드메시지입니다.");
      request.setImageUrl(wideImageUrl);
      request.setImageName("브랜드 와이드 이미지");
      request.setImageLink("https://example.com/event");
      request.addButton(new BrandMessageButton()
          .setName("이벤트 참여")
          .setLinkType(BrandMessageLinkType.WL)
          .setLinkM("https://example.com/event"));

      BrandMessageTemplateIdResponse response = sendon.kakao.createBrandMessageWideTemplate(
          KKO_SEND_PROFILE_ID,
          request
      );

      Log.d("브랜드메시지 와이드 템플릿 생성 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 와이드 템플릿 생성";
  }
}
