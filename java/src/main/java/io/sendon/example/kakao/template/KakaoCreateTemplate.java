package io.sendon.example.kakao.template;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.CreateTemplateRequestSchema;
import io.sendon.model.CreateTemplateRequestSchemaButtonsInner;
import io.sendon.model.CreateTemplateRequestSchemaButtonsInnerOneOf;
import io.sendon.model.CreateTemplateResponseSchema;

public class KakaoCreateTemplate extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      CreateTemplateRequestSchema request = createRequest();
      CreateTemplateResponseSchema response = sendon.getKakao().createTemplate(KKO_SEND_PROFILE_ID, request);
      System.out.println("응답:\n\t" + response.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  private CreateTemplateRequestSchema createRequest() {
    CreateTemplateRequestSchemaButtonsInnerOneOf button1Inner = new CreateTemplateRequestSchemaButtonsInnerOneOf();
    button1Inner.setName("웹사이트 바로가기");
    button1Inner.setType(CreateTemplateRequestSchemaButtonsInnerOneOf.TypeEnum.WL);
    button1Inner.setUrlMobile("http://example.com");
    button1Inner.setUrlPc("http://example.com");
    
    CreateTemplateRequestSchemaButtonsInner button1 = new CreateTemplateRequestSchemaButtonsInner();
    button1.setActualInstance(button1Inner);

    CreateTemplateRequestSchema dto = new CreateTemplateRequestSchema();
    dto.setTemplateName("템플릿 이름");
    dto.setTemplateContent("안녕하세요 #{이름}님, 테스트 템플릿입니다.");
    dto.addButtonsItem(button1);

    return dto;
  }
  
  @Override
  public String getDescription() {
    return "[카카오] 템플릿 생성";
  }

}