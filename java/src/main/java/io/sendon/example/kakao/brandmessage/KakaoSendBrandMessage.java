package io.sendon.example.kakao.brandmessage;

import java.util.List;
import java.util.Map;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageSendBuilder;
import io.sendon.kakao.brandmessage.response.BrandMessageSendResponse;
import io.sendon.kakao.request.ToWithVariable;

/**
 * [카카오] 브랜드메시지 발송 예제
 */
public class KakaoSendBrandMessage extends BaseScenario {

  @Override
  public void execute() {
    try {
      Map<String, String> variables = Map.of("#{고객명}", "홍길동");
      ToWithVariable recipient = new ToWithVariable(KKO_MOBILE_TO, variables);

      BrandMessageSendBuilder builder = new BrandMessageSendBuilder()
          .setSendProfileId(KKO_SEND_PROFILE_ID)
          .setTemplateCode(KKO_BRAND_MESSAGE_TEMPLATE_CODE)
          .setTo(List.of(recipient))
          .setUseCredit(Boolean.TRUE);

      BrandMessageSendResponse response = sendon.kakao.sendBrandMessage(builder);
      Log.d("브랜드메시지 발송 결과: " + gson.toJson(response));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 발송";
  }
}
