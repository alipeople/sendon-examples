/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { CreateBlocklistResponseDto, DeleteBlocklistResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'

export class AddRemoveBlocklist extends BaseScenario {
  description = '[주소록] 수신거부 추가삭제'

  async execute() {
    try {
      const result1: CreateBlocklistResponseDto = await this.sendon.contacts.createBlocklist({
        phoneNumber: "PHONENUMBER_TO_BLOCK",
      })
      console.log(`응답: ${JSON.stringify(result1, null, 2)}`)

      const result2: DeleteBlocklistResponseDto = await this.sendon.contacts.deleteBlocklist(result1.data.id)
      console.log(`응답: ${JSON.stringify(result2, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
