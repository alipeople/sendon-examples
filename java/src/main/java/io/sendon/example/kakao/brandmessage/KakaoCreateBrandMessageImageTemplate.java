package io.sendon.example.kakao.brandmessage;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageButton;
import io.sendon.kakao.brandmessage.request.BrandMessageImageTemplateRequest;
import io.sendon.kakao.brandmessage.request.BrandMessageLinkType;
import io.sendon.kakao.brandmessage.response.BrandMessageImageUploadResponse;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateIdResponse;

/**
 * [카카오] 브랜드메시지 이미지형 템플릿 생성 예제
 */
public class KakaoCreateBrandMessageImageTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      File imageFile = new File("img" + File.separator + "sample-image.jpeg");

      if (!imageFile.exists()) {
        throw new IllegalStateException("샘플 이미지 파일을 찾을 수 없습니다: " + imageFile.getAbsolutePath());
      }

      BrandMessageImageUploadResponse uploadResponse = sendon.kakao.uploadBrandMessageImage(imageFile);
      Log.d("브랜드메시지 이미지 업로드 결과: " + gson.toJson(uploadResponse));
      
      String imageUrl = uploadResponse != null ? uploadResponse.imageUrl : null;

      if (imageUrl == null || imageUrl.isBlank()) {
        Log.d("브랜드메시지 이미지 업로드에 실패했습니다.");
        return;
      }

      Log.d("업로드된 브랜드메시지 이미지 URL: " + imageUrl);

      BrandMessageImageTemplateRequest request = new BrandMessageImageTemplateRequest();
      request.setTemplateName("bm-image-" + System.currentTimeMillis());
      request.setContent("#{고객명}님을 위한 이미지형 브랜드메시지입니다.");
      request.setImageUrl(imageUrl);
      request.setImageName("브랜드 상품 이미지");
      request.setImageLink("https://example.com/product");
      request.addButton(new BrandMessageButton()
          .setName("상품 보기")
          .setLinkType(BrandMessageLinkType.WL)
          .setLinkM("https://example.com/product"));

      BrandMessageTemplateIdResponse response = sendon.kakao.createBrandMessageImageTemplate(
          KKO_SEND_PROFILE_ID,
          request
      );

      Log.d("브랜드메시지 이미지 템플릿 생성 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 이미지 템플릿 생성";
  }
}
