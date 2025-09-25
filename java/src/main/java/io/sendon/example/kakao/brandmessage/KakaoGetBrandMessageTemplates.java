package io.sendon.example.kakao.brandmessage;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageTemplateQuery;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateListResponse;

/**
 * [카카오] 브랜드메시지 템플릿 목록 조회 예제
 */
public class KakaoGetBrandMessageTemplates extends BaseScenario {

  @Override
  public void execute() {
    try {
      BrandMessageTemplateQuery query = new BrandMessageTemplateQuery()
          .setLimit(10);

      BrandMessageTemplateListResponse response = sendon.kakao.getBrandMessageTemplates(
          KKO_SEND_PROFILE_ID,
          query
      );

      Log.d("브랜드메시지 템플릿 목록 조회 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 템플릿 목록 조회";
  }
}
