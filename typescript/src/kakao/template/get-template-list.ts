/* eslint-disable no-console */

import { HttpStatusCode } from 'axios'
import { BaseScenario, KKO_SEND_PROFILE_ID } from '../../base.scenario'

export class GetTemplateList extends BaseScenario {
  description = '[카카오] 알림톡 템플릿 목록 조회'

  async execute() {
    const result1 = await this.sendon.kakao.getTemplates(KKO_SEND_PROFILE_ID, {})

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }

  }
}
