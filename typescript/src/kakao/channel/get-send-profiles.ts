/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../../base.scenario'

export class GetSendProfiles extends BaseScenario {
  description = '[카카오] 발신프로필 리스트 조회'

  async execute() {
    try {
      const result = await this.sendon.kakao.getSendProfiles()
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
