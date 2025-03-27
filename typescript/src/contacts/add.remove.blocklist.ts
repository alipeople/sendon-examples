/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { CreateBlocklistResponseDto, DeleteBlocklistResponseDto, SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario } from '../base.scenario'
import { HttpStatusCode } from 'axios'

export class AddRemoveBlocklist extends BaseScenario {
  description = '[주소록] 수신거부 추가삭제'

  async execute() {
    const result1: CreateBlocklistResponseDto = await this.sendon.contacts.createBlocklist({
      phoneNumber: "PHONENUMBER_TO_BLOCK",
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
      const result2: DeleteBlocklistResponseDto = await this.sendon.contacts.deleteBlocklist(result1.data.id)

      if (result2.code === HttpStatusCode.Ok) {
        console.log(`성공 응답: ${JSON.stringify(result2, null, 2)}`)
      } else {
        console.log(`실패 응답: ${JSON.stringify(result2, null, 2)}`)
      }

    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
