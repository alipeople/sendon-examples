/* eslint-disable no-console */
import * as fs from 'fs'

import { HttpStatusCode } from 'axios'
import { BaseScenario } from '../../base.scenario'

export class UploadFallbackImage extends BaseScenario {
  description = '[카카오] 대체문자 이미지 업로드'

  async execute() {
    const buffer = fs.readFileSync('./src/kakao/image/images/sample.jpg')
    const file = new File([buffer], 'sample.jpg', { type: 'image/jpeg' })

    const result1 = await this.sendon.kakao.uploadFallbackImage([file])

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
