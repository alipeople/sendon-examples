/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario, KKO_CHANNEL_ID, KKO_CHANNEL_PHONE_NUMBER } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class RequestChannelVerification extends BaseScenario {
  description = '[카카오] 채널 인증번호 요청'

  async execute() {
    const result1 = await this.sendon.kakao.requestAuthToken({
      channelId: KKO_CHANNEL_ID,
      phoneNumber: KKO_CHANNEL_PHONE_NUMBER,
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
