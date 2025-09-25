/* eslint-disable no-console */
import { SdoKakaoSendAlimTalkResponse } from '@alipeople/sendon-sdk-typescript';

import { HttpStatusCode } from "axios";
import {
    BaseScenario,
    KKO_MOBILE_TO,
    KKO_SEND_PROFILE_ID,
    KKO_TEMPLATE_ID,
} from "../../base.scenario";

export class SendAlimTalk extends BaseScenario {
  description = "[카카오] 알림톡 발송";

  async execute() {
    const result1: SdoKakaoSendAlimTalkResponse =
      await this.sendon.kakao.sendAlimTalk({
        sendProfileId: KKO_SEND_PROFILE_ID,
        templateId: KKO_TEMPLATE_ID,
        to: [ KKO_MOBILE_TO.to ],
      });

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`);
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`);
    }
  }
}
