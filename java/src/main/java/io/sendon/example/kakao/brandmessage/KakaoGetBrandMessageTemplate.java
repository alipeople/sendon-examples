package io.sendon.example.kakao.brandmessage;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateDetailResponse;

/**
 * [카카오] 브랜드메시지 템플릿 상세 조회 예제
 */
public class KakaoGetBrandMessageTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      BrandMessageTemplateDetailResponse response = sendon.kakao.getBrandMessageTemplate(
          KKO_SEND_PROFILE_ID,
          KKO_BRAND_MESSAGE_TEMPLATE_CODE
      );
      Log.d("브랜드메시지 템플릿 상세 조회 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 템플릿 상세 조회";
  }
}
