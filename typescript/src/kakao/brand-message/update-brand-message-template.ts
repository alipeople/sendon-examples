/* eslint-disable no-console */
import {
    SdoError,
    UpdateTextBrandMessageTemplateRequest,
} from '@alipeople/sendon-sdk-typescript'
import { HttpStatusCode } from 'axios'

import {
    BaseScenario,
    KKO_BRAND_MESSAGE_TEMPLATE_CODE,
    KKO_SEND_PROFILE_ID,
} from '../../base.scenario'

export class UpdateBrandMessageTemplate extends BaseScenario {
  description = '[카카오] 브랜드메시지 템플릿 수정'

  async execute() {
    const request: UpdateTextBrandMessageTemplateRequest = {
      templateName: `brand-message-${Date.now()}`,
      content: '업데이트된 브랜드 메시지 본문입니다. #{고객명}님에게 발송됩니다.',
      buttons: [
        {
          name: '구매하기',
          linkType: 'WL',
          linkM: 'https://example.com/purchase',
          linkP: 'https://example.com/purchase',
        },
      ],
    }

    try {
      const response = await this.sendon.kakao.updateBrandMessageTextTemplate(
        KKO_SEND_PROFILE_ID,
        KKO_BRAND_MESSAGE_TEMPLATE_CODE,
        request,
      )

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
