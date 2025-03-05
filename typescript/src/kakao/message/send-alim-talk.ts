/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'
import { SdoKakaoSendAlimTalkResponse } from '@alipeople/sendon-sdk-typescript/dist/wrapper/kakao/kakao.wrapper'

import { BaseScenario, KKO_MOBILE_TO, KKO_SEND_PROFILE_ID, KKO_TEMPLATE_ID } from '../../base.scenario'

export class SendAlimTalk extends BaseScenario {
  description = '[카카오] 알림톡 발송'

  async execute() {
    try {
      const result: SdoKakaoSendAlimTalkResponse = await this.sendon.kakao.sendAlimTalk(KKO_SEND_PROFILE_ID, {
        templateId: KKO_TEMPLATE_ID,
        recipients: [KKO_MOBILE_TO],
      })
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
