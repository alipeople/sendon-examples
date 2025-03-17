/* eslint-disable no-console */
import { SdoError, SmsMessageType } from '@alipeople/sendon-sdk-typescript'
import {
  SdoSmsGetMessageResponse,
  SdoSmsSendMessageResponse,
} from '@alipeople/sendon-sdk-typescript/dist/wrapper/sms/sms.wrapper'

import { BaseScenario, SMS_MOBILE_FROM, SMS_MOBILE_TO } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class QueryShortMessage extends BaseScenario {
  description = '[SMS] 발송문자 조회'

  async execute() {
    const result1: SdoSmsSendMessageResponse = await this.sendon.sms.send({
      type: SmsMessageType.SMS,
      from: SMS_MOBILE_FROM,
      to: SMS_MOBILE_TO,
      message: `안녕하세요. Sendon SDK Typescript 이용한 문자 발송입니다.(${Math.random().toString(36).substring(2, 5)})`,
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
      await this.sleep(5 * 1000)

      const result2: SdoSmsGetMessageResponse = await this.sendon.sms.find(result1.data.groupId)
      if (result2.code === HttpStatusCode.Ok) {
        console.log(`성공 응답: ${JSON.stringify(result2, null, 2)}`)
      } else {
        console.log(`실패 응답: ${JSON.stringify(result2, null, 2)}`)
      }
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
