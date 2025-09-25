/* eslint-disable no-console */
import { SendRcsMessageRequestDtoType } from '@alipeople/sendon-sdk-typescript'
import { SdoRcsSendMessageRequest, SdoRcsSendMessageResponseDto } from '@alipeople/sendon-sdk-typescript/dist/wrapper/rcs/rcs.wrapper'

import { HttpStatusCode } from 'axios'
import { BaseScenario, RCS_AGENCY_ID, RCS_AGENCY_KEY, RCS_BRAND_ID, RCS_BRAND_KEY, RCS_CHATBOT_ID, RCS_CLIENT_ID, RCS_CLIENT_SECRET, RCS_MESSAGEBASE_ID, RCS_MOBILE_FROM, RCS_MOBILE_TO } from '../base.scenario'

export class SendRcsMessageNow extends BaseScenario {
  description = '[RCS] 즉시 RCS 메시지 발송'

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
        campaignId: RCS_BRAND_ID,
        brandId: RCS_BRAND_ID,
        brandKey: RCS_BRAND_KEY,
        agencyId: RCS_AGENCY_ID,
        agencyKey: RCS_AGENCY_KEY,
        clientId: RCS_CLIENT_ID,
        clientSecret: RCS_CLIENT_SECRET,
        body: { }
      },
      useFallback: true,
      fallback: {
        messageType: 'LMS',
        from: RCS_MOBILE_FROM,
        isAd: true,
        title: '(광고) RCS 발송 실패시 대체 문자 메시지',
        advertiserName: '광고주 이름',
        message: '(광고) RCS 발송 실패시 대체 문자 메시지 수신거부: 0800000000 인증번호: 1234',
        blockCall: {
          numberOf080: '0800000000',
          pinNumber: '1234',
        }
      }
    }

    const result1: SdoRcsSendMessageResponseDto = await this.sendon.rcs.send(rcsMessage)

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}