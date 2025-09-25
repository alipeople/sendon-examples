package io.sendon.example.rcs;

import java.util.Arrays;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.rcs.SendonRcs.MessageType;
import io.sendon.rcs.request.Fallback;
import io.sendon.rcs.request.RbcConfig;
import io.sendon.rcs.request.RbcConfig.RbcBody;
import io.sendon.rcs.request.RcsBuilder;
import io.sendon.rcs.response.SendRcs;

public class RcsSendMessageNowScenario extends BaseScenario {

  @Override
  public void execute() {
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
            .setBody(new RbcBody())
        )
        .setFallback(new Fallback()
            .setMessageType("SMS")
            .setFrom(RCS_MOBILE_FROM)
            .setMessage("RCS 발송 실패시 대체 문자 메시지")
        )
    );
    Log.d("응답: " + gson.toJson(sendRcs));
  }

  @Override
  public String getDescription() {
    return "[RCS] 즉시 RCS 메시지 발송";
  }
}