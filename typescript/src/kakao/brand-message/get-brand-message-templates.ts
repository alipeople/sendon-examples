/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'
import { HttpStatusCode } from 'axios'

import { BaseScenario, KKO_SEND_PROFILE_ID } from '../../base.scenario'

export class GetBrandMessageTemplateList extends BaseScenario {
  description = '[카카오] 브랜드메시지 템플릿 목록 조회'

  async execute() {
    try {
      const response = await this.sendon.kakao.getBrandMessageTemplates(
        KKO_SEND_PROFILE_ID,
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
