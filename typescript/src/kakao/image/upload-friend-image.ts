/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'
import * as fs from 'fs'

import { BaseScenario } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class UploadFriendImage extends BaseScenario {
  description = '[카카오] 친구톡 이미지 업로드'

  async execute() {
    const filePath = './src/kakao/image/images/sample-image.jpeg'
    const fileName = 'sample-image.jpeg'

    const imageFile = fs.readFileSync(filePath)
    const file = new File([imageFile], fileName, { type: 'image/jpeg' })
    const result1 = await this.sendon.kakao.uploadFriendTalkImage(file)

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }

  }
}
