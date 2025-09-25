package io.sendon.example.kakao.brandmessage;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.response.BrandMessageDeleteResponse;

/**
 * [카카오] 브랜드메시지 템플릿 삭제 예제
 */
public class KakaoDeleteBrandMessageTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      BrandMessageDeleteResponse response = sendon.kakao.deleteBrandMessageTemplate(
          KKO_SEND_PROFILE_ID,
          KKO_BRAND_MESSAGE_TEMPLATE_CODE
      );
      Log.d("브랜드메시지 템플릿 삭제 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 템플릿 삭제";
  }
}
