/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'
import * as fs from 'fs'

import { BaseScenario } from '../../base.scenario'

export class UploadFriendImage extends BaseScenario {
  description = '[카카오] 친구톡 이미지 업로드'

  async execute() {
    try {
      const imageFile = fs.readFileSync('./src/kakao/image/images/sample.jpg')
      const file = new File([imageFile], 'sample.jpg', { type: 'image/jpeg' })
      const result = await this.sendon.kakao.uploadFriendTalkImage(file)
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
