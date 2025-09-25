package io.sendon.example.kakao.brandmessage;

import java.io.File;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageButton;
import io.sendon.kakao.brandmessage.request.BrandMessageLinkType;
import io.sendon.kakao.brandmessage.request.BrandMessageWideItemListTemplateRequest;
import io.sendon.kakao.brandmessage.request.BrandMessageWideItemListTemplateRequest.MainWideItem;
import io.sendon.kakao.brandmessage.request.BrandMessageWideItemListTemplateRequest.SubWideItem;
import io.sendon.kakao.brandmessage.response.BrandMessageImageUploadResponse;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateIdResponse;

/**
 * [카카오] 브랜드메시지 와이드 아이템리스트 템플릿 생성 예제
 */
public class KakaoCreateBrandMessageWideItemListTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      File wideImageFile = new File("img" + File.separator + "sample-wide-image.jpeg");

      File subImageFile = new File("img" + File.separator + "sample-image.jpeg");

      if (!wideImageFile.exists()) {
        throw new IllegalStateException("와이드 아이템용 이미지 파일이 없습니다: "
            + wideImageFile.getAbsolutePath());
      }

      if (!subImageFile.exists()) {
        throw new IllegalStateException("서브 아이템용 이미지 파일이 없습니다: "
            + subImageFile.getAbsolutePath());
      }

      BrandMessageImageUploadResponse mainImageUpload = sendon.kakao.uploadBrandMessageWideImage(wideImageFile);
      String mainImageUrl = mainImageUpload != null ? mainImageUpload.imageUrl : null;

      BrandMessageImageUploadResponse subImageUpload = sendon.kakao.uploadBrandMessageImage(subImageFile);
      String subImageUrl = subImageUpload != null ? subImageUpload.imageUrl : null;

      if (mainImageUrl == null || mainImageUrl.isBlank() || subImageUrl == null || subImageUrl.isBlank()) {
        Log.d("와이드 아이템리스트 템플릿에 사용할 이미지 업로드에 실패했습니다.");
        return;
      }

      Log.d("업로드된 메인 와이드 이미지 URL: " + mainImageUrl);
      Log.d("업로드된 서브 아이템 이미지 URL: " + subImageUrl);

      BrandMessageWideItemListTemplateRequest request = new BrandMessageWideItemListTemplateRequest();
      request.setTemplateName("bm-wide-list-" + System.currentTimeMillis());
      request.setHeader("이번 주 베스트");
      request.setMainWideItem(new MainWideItem()
          .setTitle("1위 상품")
          .setImageUrl(mainImageUrl)
          .setLinkM("https://example.com/product/1"));

      request.addSubWideItem(new SubWideItem()
          .setTitle("2위 상품")
          .setImageUrl(subImageUrl)
          .setLinkM("https://example.com/product/2"));
      request.addSubWideItem(new SubWideItem()
          .setTitle("3위 상품")
          .setImageUrl(subImageUrl)
          .setLinkM("https://example.com/product/3"));
      request.addSubWideItem(new SubWideItem()
          .setTitle("4위 상품")
          .setImageUrl(subImageUrl)
          .setLinkM("https://example.com/product/4"));

      request.addButton(new BrandMessageButton()
          .setName("상품 전체 보기")
          .setLinkType(BrandMessageLinkType.WL)
          .setLinkM("https://example.com/best"));

      BrandMessageTemplateIdResponse response = sendon.kakao.createBrandMessageWideItemListTemplate(
          KKO_SEND_PROFILE_ID,
          request
      );

      Log.d("브랜드메시지 와이드 아이템리스트 템플릿 생성 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 와이드 아이템리스트 템플릿 생성";
  }
}
