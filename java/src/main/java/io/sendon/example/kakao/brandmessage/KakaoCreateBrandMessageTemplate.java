package io.sendon.example.kakao.brandmessage;

import java.util.List;
import java.util.Map;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.kakao.brandmessage.request.BrandMessageButton;
import io.sendon.kakao.brandmessage.request.BrandMessageLinkType;
import io.sendon.kakao.brandmessage.request.BrandMessageSendBuilder;
import io.sendon.kakao.brandmessage.request.BrandMessageTextTemplateRequest;
import io.sendon.kakao.brandmessage.response.BrandMessageSendResponse;
import io.sendon.kakao.brandmessage.response.BrandMessageTemplateIdResponse;
import io.sendon.kakao.request.ToWithVariable;

/**
 * [카카오] 브랜드메시지 텍스트형 템플릿 생성 및 발송 예제
 */
public class KakaoCreateBrandMessageTemplate extends BaseScenario {

  @Override
  public void execute() {
    try {
      BrandMessageTextTemplateRequest request = new BrandMessageTextTemplateRequest();
      request.setTemplateName("bm-text-" + System.currentTimeMillis());
      request.setContent("안녕하세요 #{고객명}님, 브랜드 메시지 테스트입니다.");
      request.addButton(new BrandMessageButton()
          .setName("상세보기")
          .setLinkType(BrandMessageLinkType.WL)
          .setLinkM("https://example.com/mobile")
          .setLinkP("https://example.com"));

      BrandMessageTemplateIdResponse createResponse = sendon.kakao.createBrandMessageTextTemplate(
          KKO_SEND_PROFILE_ID,
          request
      );

      Log.d("브랜드메시지 텍스트 템플릿 생성 결과: " + gson.toJson(createResponse));

      if (createResponse != null && createResponse.templateCode != null && !createResponse.templateCode.isEmpty()) {
        Map<String, String> variables = Map.of("#{고객명}", "홍길동");
        ToWithVariable recipient = new ToWithVariable(KKO_MOBILE_TO, variables);

        BrandMessageSendBuilder builder = new BrandMessageSendBuilder()
            .setSendProfileId(KKO_SEND_PROFILE_ID)
            .setTemplateCode(createResponse.templateCode)
            .setTo(List.of(recipient));

        BrandMessageSendResponse sendResponse = sendon.kakao.sendBrandMessage(builder);
        Log.d("브랜드메시지 발송 결과: " + gson.toJson(sendResponse));
      }
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[카카오] 브랜드메시지 텍스트 템플릿 생성 및 발송";
  }
}
