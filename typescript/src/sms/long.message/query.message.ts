/* eslint-disable no-console */
import { SdoError, SmsMessageType } from '@alipeople/sendon-sdk-typescript'
import {
  SdoSmsGetMessageResponse,
  SdoSmsSendMessageResponse,
} from '@alipeople/sendon-sdk-typescript/dist/wrapper/sms/sms.wrapper'

import { BaseScenario, SMS_MOBILE_FROM, SMS_MOBILE_TO } from '../../base.scenario'

export class QueryLongMessage extends BaseScenario {
  description = '[LMS] 발송문자 조회'

  async execute() {
    try {
      const result1: SdoSmsSendMessageResponse = await this.sendon.sms.send({
        type: SmsMessageType.LMS,
        from: SMS_MOBILE_FROM,
        to: SMS_MOBILE_TO,
        title: '테스트 문자',
        message: `안녕하세요. Sendon SDK Typescript 이용한 문자 발송입니다.(${Math.random().toString(36).substring(2, 5)})`,
      })
      console.log(`응답: ${JSON.stringify(result1, null, 2)}`)

      await this.sleep(20 * 1000)

      const result2: SdoSmsGetMessageResponse = await this.sendon.sms.find(result1.data.groupId)
      console.log(`응답: ${JSON.stringify(result2, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
