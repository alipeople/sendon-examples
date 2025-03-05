/* eslint-disable no-console */
import { CostGetResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'

export class GetCost extends BaseScenario {
  description = '[포인트] 단가 조회'

  async execute() {
    try {
      const result: CostGetResponseDto = await this.sendon.point.getCost()
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
