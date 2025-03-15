/* eslint-disable no-console */
import { CostGetResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'
import { HttpStatusCode } from 'axios'

export class GetCost extends BaseScenario {
  description = '[포인트] 단가 조회'

  async execute() {
    const result1: CostGetResponseDto = await this.sendon.point.getCost()

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
