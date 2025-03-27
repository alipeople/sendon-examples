/* eslint-disable no-console */
import { SdoError, SmsMessageType } from '@alipeople/sendon-sdk-typescript'
import {
  SdoSmsSendMessageResponse
} from '@alipeople/sendon-sdk-typescript/dist/wrapper/sms/sms.wrapper'
import * as fs from 'fs'
import * as path from 'path'

import { BaseScenario, SMS_MOBILE_FROM, SMS_MOBILE_TO } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class UploadLongMessageImage extends BaseScenario {
  description = '[MMS] 이미지 업로드, 발송'

  async execute() {
    // 이미지 준비
    const imagePath = path.resolve(__dirname, './aligo.png')
    const imageBuffer = fs.readFileSync(imagePath)
    const imageBlob = new Blob([imageBuffer], { type: 'image/png' })
    const imageFile = new File([imageBlob], 'aligo.png', { type: 'image/png' })

    // 이미지 업로드
    const files: Array<File> = [imageFile]
    console.log('이미지 업로드 중...')

    const uploadPromise = this.sendon.sms.uploadImages(files)
    const abortController = new AbortController()
    const sleepPromise = this.sleep(20000, abortController.signal)
    const result1 = await Promise.race([uploadPromise, sleepPromise])
    abortController.abort()

    if (result1?.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)

      // 업로드된 이미지와 함께 메시지 발송
      const result2: SdoSmsSendMessageResponse = await this.sendon.sms.send({
        type: SmsMessageType.MMS,
        from: SMS_MOBILE_FROM,
        to: SMS_MOBILE_TO,
        title: '테스트 문자',
        message: `안녕하세요. Sendon SDK Typescript 이용한 문자 발송입니다.(${Math.random().toString(36).substring(2, 5)})`,
        images: [result1.data.images[0].id],
      })

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
