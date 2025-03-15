/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { GetBlocklistResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'
import { HttpStatusCode } from 'axios'

export class GetBlocklist extends BaseScenario {
  description = '[주소록] 차단목록 조회'

  async execute() {
    const result1: GetBlocklistResponseDto = await this.sendon.contacts.getBlocklist({
      cursor: 3,
      limit: 3,
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
