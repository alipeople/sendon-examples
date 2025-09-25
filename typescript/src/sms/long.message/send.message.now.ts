/* eslint-disable no-console */
import { SdoSmsSendMessageResponse, SmsMessageType } from '@alipeople/sendon-sdk-typescript'

import { HttpStatusCode } from 'axios'
import { BaseScenario, SMS_MOBILE_FROM, SMS_MOBILE_TO } from '../../base.scenario'

export class SendLongMessageNow extends BaseScenario {
  description = '[LMS] 즉시문자 발송'

  async execute() {
    const result1: SdoSmsSendMessageResponse = await this.sendon.sms.send({
      type: SmsMessageType.LMS,
      from: SMS_MOBILE_FROM,
      to: SMS_MOBILE_TO,
      title: '테스트 문자',
      message: `안녕하세요. Sendon SDK Typescript 이용한 문자 발송입니다.(${Math.random().toString(36).substring(2, 5)})`,
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
