/* eslint-disable no-console */
import { PointsListResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'
import { HttpStatusCode } from 'axios'

export class GetPoint extends BaseScenario {
  description = '[포인트] 포인트 조회'

  async execute() {
    const result1: PointsListResponseDto = await this.sendon.point.listPoints()

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
