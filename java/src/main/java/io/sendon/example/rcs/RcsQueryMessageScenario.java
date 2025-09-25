package io.sendon.example.rcs;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.rcs.SendonRcs.MessageType;
import io.sendon.rcs.request.Fallback;
import io.sendon.rcs.request.RbcConfig;
import io.sendon.rcs.request.RbcConfig.RbcBody;
import io.sendon.rcs.request.RcsBuilder;
import io.sendon.rcs.response.GetRcs;
import io.sendon.rcs.response.SendRcs;

public class RcsQueryMessageScenario extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      RbcBody body = new RbcBody();
      body.description = "안녕하세요. Sendon SDK Java를 이용한 RCS 발송입니다.(" + generateRandomCode() + ")";

      SendRcs sendRcs = sendon.rcs.send(new RcsBuilder()
          .setType(MessageType.RCS_TSM)
          .setFrom(RCS_MOBILE_FROM)
          .setTo(Arrays.asList(RCS_MOBILE_TO))
          .setUseFallback(true)
          .setRbcConfig(new RbcConfig()
              .setChatbotId(RCS_CHATBOT_ID)
              .setMessagebaseId(RCS_MESSAGEBASE_ID)
              .setHeader(0)
              .setBrandId(RCS_BRAND_ID)
              .setBrandKey(RCS_BRAND_KEY)
              .setAgencyId(RCS_AGENCY_ID)
              .setAgencyKey(RCS_AGENCY_KEY)
              .setClientId(RCS_CLIENT_ID)
              .setClientSecret(RCS_CLIENT_SECRET)
              .setBody(body)
          )
          .setFallback(new Fallback()
              .setMessageType("SMS")
              .setFrom(RCS_MOBILE_FROM)
              .setMessage("RCS 발송 실패시 대체 문자 메시지")
          )
      );
      Log.d("SendRcs: " + gson.toJson(sendRcs));

      sleep(5000);

      GetRcs getRcs = sendon.rcs.find(sendRcs.data.groupId);
      Log.d("GetRcs: " + gson.toJson(getRcs));
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[RCS] 발송한 RCS 메시지 조회";
  }
}