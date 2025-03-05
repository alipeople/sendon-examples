package io.sendon.example.kakao.template;

import java.math.BigDecimal;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.GetTemplateResponseSchema;

public class KakaoGetTemplate extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      String keyword = "";
      BigDecimal limit = BigDecimal.TEN;
      String cursor = "";
      String status = "";
      String sort = "";

      GetTemplateResponseSchema response = sendon.getKakao().getTemplate(
        KKO_SEND_PROFILE_ID, KKO_TEMPLATE_ID
      );

      System.out.println("응답:\n\t" + response.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 템플릿 조회";
  }

}