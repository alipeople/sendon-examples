/* eslint-disable no-console */
import { PointsListResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'

export class GetPoint extends BaseScenario {
  description = '[포인트] 포인트 조회'

  async execute() {
    try {
      const result: PointsListResponseDto = await this.sendon.point.listPoints()
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
