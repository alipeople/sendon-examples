/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { ListUserNumbersResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'
import { HttpStatusCode } from 'axios'

export class GetNumbers extends BaseScenario {
  description = '[발신번호] 발신번호 목록조회'

  async execute() {
    const result1: ListUserNumbersResponseDto = await this.sendon.sender.listUserNumbers()

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
