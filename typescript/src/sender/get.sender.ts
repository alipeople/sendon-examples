/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { ListUserNumbersResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'

export class GetNumbers extends BaseScenario {
  description = '[발신번호] 발신번호 목록조회'

  async execute() {
    try {
      const result1: ListUserNumbersResponseDto = await this.sendon.sender.listUserNumbers()
      console.log(`응답: ${JSON.stringify(result1, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
