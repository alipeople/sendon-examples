/* eslint-disable no-console */
import {
    CreateTextBrandMessageTemplateRequest,
    SdoError,
} from '@alipeople/sendon-sdk-typescript'
import { HttpStatusCode } from 'axios'

import {
    BaseScenario,
    KKO_MOBILE_TO,
    KKO_SEND_PROFILE_ID,
} from '../../base.scenario'

export class CreateBrandMessageTemplate extends BaseScenario {
  description = '[카카오] 브랜드메시지 템플릿 생성/발송'

  async execute() {
    const request: CreateTextBrandMessageTemplateRequest = {
      templateName: `brand-message-${Date.now()}`,
      content: '안녕하세요 #{고객명}님, 브랜드 메시지 테스트입니다.',
      buttons: [
        {
          name: '상세보기',
          linkType: 'WL',
          linkM: 'https://example.com/mobile',
          linkP: 'https://example.com',
        },
      ],
    }

    try {
      const createResponse = await this.sendon.kakao.createBrandMessageTextTemplate(
        KKO_SEND_PROFILE_ID,
        request,
      )
      const body = createResponse

      if (body.code === HttpStatusCode.Ok) {
        console.log(`템플릿 생성 성공: ${JSON.stringify(body, null, 2)}`)
        const templateCode = body.data.templateCode
        console.log(`생성된 템플릿 코드: ${templateCode}`)

        const recipients = [
          {
            phone: KKO_MOBILE_TO.to,
            ...(KKO_MOBILE_TO.variables
              ? { variables: KKO_MOBILE_TO.variables }
              : {}),
          },
        ]

        const sendResponse = await this.sendon.kakao.sendBrandMessage({
          sendProfileId: KKO_SEND_PROFILE_ID,
          templateCode,
          to: recipients,
        })

        if (sendResponse.code === HttpStatusCode.Ok) {
          console.log(`템플릿 기반 발송 성공: ${JSON.stringify(sendResponse, null, 2)}`)
        } else {
          console.log(`템플릿 기반 발송 실패: ${JSON.stringify(sendResponse, null, 2)}`)
        }
      } else {
        console.log(`템플릿 생성 실패: ${JSON.stringify(body, null, 2)}`)
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
