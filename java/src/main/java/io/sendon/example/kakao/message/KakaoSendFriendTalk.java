package io.sendon.example.kakao.message;

import java.util.HashMap;
import java.util.Map;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.CreateTemplateRequestSchemaButtonsInner;
import io.sendon.model.CreateTemplateRequestSchemaButtonsInnerOneOf;
import io.sendon.model.SendAlimTalkRequestBodySchemaRecipientsInner;
import io.sendon.model.SendAlimTalkRequestBodySchemaRecipientsInnerOneOf;
import io.sendon.model.SendFriendTalkRequestSchema;
import io.sendon.model.SendFriendTalkRequestSchema.MessageTypeEnum;
import io.sendon.model.SendFriendTalkResponseSchema;

public class KakaoSendFriendTalk extends BaseScenario {

    @Override
    public void execute() throws InterruptedException {
        try {
          SendFriendTalkRequestSchema request = createRequest();
          SendFriendTalkResponseSchema response = sendon.getKakao().sendFriendTalkAsync(
            KKO_SEND_PROFILE_ID, 
            request
          );
          System.out.println("응답:\n\t" + response.toJson());

        } catch (ApiException e) {
          handleException(e);
        }
    }

    private SendFriendTalkRequestSchema createRequest() {
      SendAlimTalkRequestBodySchemaRecipientsInnerOneOf recipientInner = new SendAlimTalkRequestBodySchemaRecipientsInnerOneOf();
      Map<String, String> variables = new HashMap<>();
      variables.put("#{고객명}", "홍길동");
      recipientInner.setTo(KKO_MOBILE_TO);
      recipientInner.setVariables(variables);
      
      SendAlimTalkRequestBodySchemaRecipientsInner recipient = new SendAlimTalkRequestBodySchemaRecipientsInner();
      recipient.setActualInstance(recipientInner);
      
      CreateTemplateRequestSchemaButtonsInnerOneOf button1Inner = new CreateTemplateRequestSchemaButtonsInnerOneOf();
      button1Inner.setName("웹사이트 바로가기");
      button1Inner.setType(CreateTemplateRequestSchemaButtonsInnerOneOf.TypeEnum.WL);
      button1Inner.setUrlMobile("http://example.com");
      button1Inner.setUrlPc("http://example.com");
      
      CreateTemplateRequestSchemaButtonsInner button1 = new CreateTemplateRequestSchemaButtonsInner();
      button1.setActualInstance(button1Inner);

      SendFriendTalkRequestSchema dto = new SendFriendTalkRequestSchema();
      dto.setMessage("""
        안녕하세요. #{이름}님.
        SDK를 이용한 친구톡 발송 테스트입니다.
      """);
      dto.setIsAd(false);
      dto.addRecipientsItem(recipient);
      dto.setMessageType(MessageTypeEnum.TALK);
      dto.addButtonsItem(button1);
      return dto;
    }

    @Override
    public String getDescription() {
        return "[카카오] 친구톡 발송";
    }
  
}
