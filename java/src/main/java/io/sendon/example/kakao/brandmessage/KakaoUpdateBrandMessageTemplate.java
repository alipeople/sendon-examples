package io.sendon.example.kakao.brandmessage;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageTextTemplateRequest;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateIdResponse;

/**
 * [카카오] 브랜드메시지 텍스트 템플릿 수정 예제
 */
public class KakaoUpdateBrandMessageTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      BrandMessageTextTemplateRequest request = new BrandMessageTextTemplateRequest();
      request.setTemplateName("bm-text-update-" + System.currentTimeMillis());
      request.setContent("#{고객명}님, 업데이트된 브랜드메시지입니다.");

      BrandMessageTemplateIdResponse response = sendon.kakao.updateBrandMessageTextTemplate(
          KKO_SEND_PROFILE_ID,
          KKO_BRAND_MESSAGE_TEMPLATE_CODE,
          request
      );

      Log.d("브랜드메시지 텍스트 템플릿 수정 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 텍스트 템플릿 수정";
  }
}
