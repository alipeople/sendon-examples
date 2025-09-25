/* eslint-disable no-console */
import { SendRcsMessageRequestDtoType } from '@alipeople/sendon-sdk-typescript'
import { SdoRcsGetMessageResponseDto, SdoRcsSendMessageRequest, SdoRcsSendMessageResponseDto } from '@alipeople/sendon-sdk-typescript/dist/wrapper/rcs/rcs.wrapper'

import { HttpStatusCode } from 'axios'
import { BaseScenario, RCS_AGENCY_ID, RCS_AGENCY_KEY, RCS_BRAND_ID, RCS_BRAND_KEY, RCS_CHATBOT_ID, RCS_CLIENT_ID, RCS_CLIENT_SECRET, RCS_MESSAGEBASE_ID, RCS_MOBILE_FROM, RCS_MOBILE_TO } from '../base.scenario'

export class QueryRcsMessage extends BaseScenario {
  description = '[RCS] 발송한 RCS 메시지 조회'

  async execute() {
    const rcsMessage: SdoRcsSendMessageRequest = {
      type: SendRcsMessageRequestDtoType.RCS_TSM,
      from: RCS_MOBILE_FROM,
      to: RCS_MOBILE_TO,
      useCredit: false,
      rbc: {
        chatbotId: RCS_CHATBOT_ID,
        messagebaseId: RCS_MESSAGEBASE_ID,
        header: '0',
        brandId: RCS_BRAND_ID,
        brandKey: RCS_BRAND_KEY,
        agencyId: RCS_AGENCY_ID,
        agencyKey: RCS_AGENCY_KEY,
        clientId: RCS_CLIENT_ID,
        clientSecret: RCS_CLIENT_SECRET,
        body: {
          title: 'RCS 테스트 메시지',
          description: `안녕하세요. Sendon SDK Typescript 이용한 RCS 발송입니다.(${Math.random().toString(36).substring(2, 5)})`
        }
      },
      useFallback: true,
      fallback: {
        messageType: 'SMS',
        from: RCS_MOBILE_FROM,
        message: 'RCS 발송 실패시 대체 문자 메시지'
      }
    }

    const result1: SdoRcsSendMessageResponseDto = await this.sendon.rcs.send(rcsMessage)

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
      await this.sleep(5 * 1000)

      const result2: SdoRcsGetMessageResponseDto = await this.sendon.rcs.find(result1.data.groupId)
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