/* eslint-disable no-console */
import { SdoError, SmsMessageType } from '@alipeople/sendon-sdk-typescript'
import { SdoSmsSendMessageResponse } from '@alipeople/sendon-sdk-typescript/dist/wrapper/sms/sms.wrapper'

import { BaseScenario, SMS_MOBILE_FROM, SMS_MOBILE_TO } from '../../base.scenario'

export class SendShortMessageNow extends BaseScenario {
  description = '[SMS] 즉시문자 발송'

  async execute() {
    try {
      const result: SdoSmsSendMessageResponse = await this.sendon.sms.send({
        type: SmsMessageType.SMS,
        from: SMS_MOBILE_FROM,
        to: SMS_MOBILE_TO,
        message: `안녕하세요. Sendon SDK Typescript 이용한 문자 발송입니다.(${Math.random().toString(36).substring(2, 5)})`,
      })
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
