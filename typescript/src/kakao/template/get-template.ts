/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario, KKO_SEND_PROFILE_ID, KKO_TEMPLATE_ID } from '../../base.scenario'

export class GetTemplate extends BaseScenario {
  description = '[카카오] 템플릿 조회'

  async execute() {
    try {
      const result = await this.sendon.kakao.getTemplate(KKO_SEND_PROFILE_ID, KKO_TEMPLATE_ID)
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
