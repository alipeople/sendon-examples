/* eslint-disable no-console */

import { HttpStatusCode } from 'axios'
import { BaseScenario, KKO_CHANNEL_ID, KKO_CHANNEL_PHONE_NUMBER } from '../../base.scenario'

export class RegisterChannel extends BaseScenario {
  description = '[카카오] 채널 등록'

  async execute() {
    const result1 = await this.sendon.kakao.registerChannel({
      channelId: KKO_CHANNEL_ID,
      phoneNumber: KKO_CHANNEL_PHONE_NUMBER,
      token: '123456', // 카카오톡으로 받은 인증번호
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }

  }
}
