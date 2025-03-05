package io.sendon.example.kakao.message;

import java.util.HashMap;
import java.util.Map;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.SendAlimTalkRequestBodySchema;
import io.sendon.model.SendAlimTalkRequestBodySchemaRecipientsInner;
import io.sendon.model.SendAlimTalkRequestBodySchemaRecipientsInnerOneOf;
import io.sendon.model.SendAlimTalkResponseSchema;

public class KakaoSendAlimTalk extends BaseScenario {

    @Override
    public void execute() throws InterruptedException {
        try {
          SendAlimTalkRequestBodySchema request = createRequest();
          SendAlimTalkResponseSchema response = sendon.getKakao().sendAlimTalkAsync(
            KKO_SEND_PROFILE_ID, 
            request
          );
          System.out.println("응답:\n\t" + response.toJson());

        } catch (ApiException e) {
          handleException(e);
        }
    }

    private SendAlimTalkRequestBodySchema createRequest() {
      SendAlimTalkRequestBodySchemaRecipientsInnerOneOf recipientInner = new SendAlimTalkRequestBodySchemaRecipientsInnerOneOf();
      Map<String, String> variables = new HashMap<>();
      variables.put("#{고객명}", "홍길동");
      recipientInner.setTo(KKO_MOBILE_TO);
      recipientInner.setVariables(variables);

      SendAlimTalkRequestBodySchemaRecipientsInner recipient = new SendAlimTalkRequestBodySchemaRecipientsInner();
      recipient.setActualInstance(recipientInner);

      SendAlimTalkRequestBodySchema dto = new SendAlimTalkRequestBodySchema();
      dto.setTemplateId(KKO_TEMPLATE_ID);
      dto.addRecipientsItem(recipient);
      return dto;
    }

    @Override
    public String getDescription() {
        return "[카카오] 알림톡 발송";
    }
  
}
