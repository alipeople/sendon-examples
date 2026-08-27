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
            // 광고성 발송 여부. true 면 발송 전 수신거부 목록과 대조해 등록된 번호를 제외한다.
            // 이 예제는 header 0(정보성)이라 false 다. 광고로 보낼 때는 true 로 지정한다.
            // 통합 템플릿(RCS 통합템플릿)은 메시지에 광고/정보성 구분값이 담기지 않아 발송 시점에
            // 판별할 수 없으므로 항상 true 로 보낸다. 미지정 시에는 서버가 안전하게 대조한다.
            .setIsAd(false)
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