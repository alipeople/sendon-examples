package io.sendon.example.kakao.brandmessage;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageButton;
import io.sendon.kakao.brandmessage.request.BrandMessageCarouselFeedTemplateRequest;
import io.sendon.kakao.brandmessage.request.BrandMessageCarouselFeedTemplateRequest.CarouselCard;
import io.sendon.kakao.brandmessage.request.BrandMessageCarouselFeedTemplateRequest.CarouselTail;
import io.sendon.kakao.brandmessage.request.BrandMessageLinkType;
import io.sendon.kakao.brandmessage.response.BrandMessageImageUploadResponse;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateIdResponse;

/**
 * [카카오] 브랜드메시지 캐러셀 템플릿 생성 예제
 */
public class KakaoCreateBrandMessageCarouselTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      File cardImageFile = new File("img" + File.separator + "sample-image.jpeg");

      if (!cardImageFile.exists()) {
        throw new IllegalStateException("캐러셀 카드용 이미지 파일을 찾을 수 없습니다: "
            + cardImageFile.getAbsolutePath());
      }

      BrandMessageImageUploadResponse firstCardUpload = sendon.kakao.uploadBrandMessageImage(cardImageFile);
      String firstCardImageUrl = firstCardUpload != null ? firstCardUpload.imageUrl : null;

      BrandMessageImageUploadResponse secondCardUpload = sendon.kakao.uploadBrandMessageImage(cardImageFile);
      String secondCardImageUrl = secondCardUpload != null ? secondCardUpload.imageUrl : null;

      if (firstCardImageUrl == null || firstCardImageUrl.isBlank()
          || secondCardImageUrl == null || secondCardImageUrl.isBlank()) {
        Log.d("캐러셀 카드 이미지 업로드에 실패했습니다.");
        return;
      }

      Log.d("업로드된 캐러셀 카드 이미지 URL(1): " + firstCardImageUrl);
      Log.d("업로드된 캐러셀 카드 이미지 URL(2): " + secondCardImageUrl);

      BrandMessageCarouselFeedTemplateRequest request = new BrandMessageCarouselFeedTemplateRequest();
      request.setTemplateName("bm-carousel-" + System.currentTimeMillis());

      request.addCard(new CarouselCard()
          .setHeader("베스트셀러")
          .setContent("추천 상품을 만나보세요.")
          .setImageUrl(firstCardImageUrl)
          .addButton(new BrandMessageButton()
              .setName("구매하기")
              .setLinkType(BrandMessageLinkType.WL)
              .setLinkM("https://example.com/product/best1")));

      request.addCard(new CarouselCard()
          .setHeader("이번 주 신상")
          .setContent("새로운 상품을 확인해 보세요.")
          .setImageUrl(secondCardImageUrl)
          .addButton(new BrandMessageButton()
              .setName("자세히 보기")
              .setLinkType(BrandMessageLinkType.WL)
              .setLinkM("https://example.com/product/new")));

      request.setTail(new CarouselTail()
          .setLinkM("https://example.com/more"));

      BrandMessageTemplateIdResponse response = sendon.kakao.createBrandMessageCarouselFeedTemplate(
          KKO_SEND_PROFILE_ID,
          request
      );

      Log.d("브랜드메시지 캐러셀 템플릿 생성 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 캐러셀 템플릿 생성";
  }
}
