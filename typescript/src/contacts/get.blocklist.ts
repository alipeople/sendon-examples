/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { GetBlocklistResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'

export class GetBlocklist extends BaseScenario {
  description = '[주소록] 차단목록 조회'

  async execute() {
    try {
      const result: GetBlocklistResponseDto = await this.sendon.contacts.getBlocklist({
        cursor: 3,
        limit: 3,
      })
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
