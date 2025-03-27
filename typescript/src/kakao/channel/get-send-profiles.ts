/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class GetSendProfiles extends BaseScenario {
  description = '[카카오] 발신프로필 리스트 조회'

  async execute() {
    const result1 = await this.sendon.kakao.getSendProfiles()

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
