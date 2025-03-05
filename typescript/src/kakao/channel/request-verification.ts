/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario, KKO_CHANNEL_ID, KKO_CHANNEL_PHONE_NUMBER } from '../../base.scenario'

export class RequestChannelVerification extends BaseScenario {
  description = '[카카오] 채널 인증번호 요청'

  async execute() {
    try {
      const result = await this.sendon.kakao.requestAuthToken({
        channelId: KKO_CHANNEL_ID,
        phoneNumber: KKO_CHANNEL_PHONE_NUMBER,
      })
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
