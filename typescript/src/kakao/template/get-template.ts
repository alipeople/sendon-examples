/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario, KKO_SEND_PROFILE_ID, KKO_TEMPLATE_ID } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class GetTemplate extends BaseScenario {
  description = '[카카오] 템플릿 조회'

  async execute() {
    const result1 = await this.sendon.kakao.getTemplate(KKO_SEND_PROFILE_ID, KKO_TEMPLATE_ID)

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
