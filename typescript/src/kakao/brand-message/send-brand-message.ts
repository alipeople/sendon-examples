/* eslint-disable no-console */
import { SdoError, SdoKakaoSendBrandMessageResponse } from '@alipeople/sendon-sdk-typescript'
import { HttpStatusCode } from 'axios'

import {
    BaseScenario,
    KKO_BRAND_MESSAGE_TEMPLATE_CODE,
    KKO_MOBILE_TO,
    KKO_SEND_PROFILE_ID,
} from '../../base.scenario'

export class SendBrandMessage extends BaseScenario {
  description = '[카카오] 브랜드메시지 발송'

  async execute() {
    try {
      const recipients = [
        {
          phone: KKO_MOBILE_TO.to,
          ...(KKO_MOBILE_TO.variables ? { variables: KKO_MOBILE_TO.variables } : {}),
        },
      ]

      const response: SdoKakaoSendBrandMessageResponse =
        await this.sendon.kakao.sendBrandMessage({
          sendProfileId: KKO_SEND_PROFILE_ID,
          templateCode: KKO_BRAND_MESSAGE_TEMPLATE_CODE,
          to: recipients,
        })

      if (response.code === HttpStatusCode.Ok) {
        console.log(`성공 응답: ${JSON.stringify(response, null, 2)}`)
      } else {
        console.log(`실패 응답: ${JSON.stringify(response, null, 2)}`)
      }
    } catch (error) {
      const err = error as SdoError
      console.error(`에러 발생: ${err.message}`)
      if (err.response?.data) {
        console.error(`응답 본문: ${JSON.stringify(err.response.data, null, 2)}`)
      }
    }
  }
}
