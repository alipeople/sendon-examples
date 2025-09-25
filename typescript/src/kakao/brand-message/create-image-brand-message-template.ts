/* eslint-disable no-console */
import * as fs from 'fs'

import {
    CreateImageBrandMessageTemplateRequest,
    SdoError,
    UploadBrandMessageImage200Response,
} from '@alipeople/sendon-sdk-typescript'
import { HttpStatusCode } from 'axios'

import {
    BaseScenario,
    KKO_MOBILE_TO,
    KKO_SEND_PROFILE_ID,
} from '../../base.scenario'
const SAMPLE_IMAGE_PATH = './src/kakao/assets/images/sample-image.jpeg'

export class CreateImageBrandMessageTemplate extends BaseScenario {
  description = '[카카오] 브랜드메시지 이미지 업로드/템플릿 생성/발송'

  async execute() {
    try {
      const buffer = fs.readFileSync(SAMPLE_IMAGE_PATH)
      const imageFile = new File([new Uint8Array(buffer)], 'sample-image.jpeg', {
        type: 'image/jpeg',
      })

      const uploadResponse: UploadBrandMessageImage200Response =
        await this.sendon.kakao.uploadBrandMessageImage(imageFile)

      if (uploadResponse.code !== HttpStatusCode.Ok) {
        console.log(`이미지 업로드 실패: ${JSON.stringify(uploadResponse, null, 2)}`)
        return
      }

      const imageUrl = uploadResponse.data.image
      console.log(`업로드된 브랜드메시지 이미지 URL: ${imageUrl}`)

      const templateRequest: CreateImageBrandMessageTemplateRequest = {
        templateName: `brand-message-${Date.now()}`,
        content: '이미지 템플릿 본문입니다. #{고객명}님 확인해주세요.',
        imageUrl,
        imageName: '브랜드 상품 이미지',
        imageLink: 'https://example.com/product',
        buttons: [
          {
            name: '이미지 이동',
            linkType: 'WL',
            linkM: 'https://example.com/mobile',
            linkP: 'https://example.com',
          },
        ],
      }

      const createResponse = await this.sendon.kakao.createBrandMessageImageTemplate(
        KKO_SEND_PROFILE_ID,
        templateRequest,
      )
      const body = createResponse

      if (body.code !== HttpStatusCode.Ok) {
        console.log(`이미지 템플릿 생성 실패: ${JSON.stringify(body, null, 2)}`)
        return
      }

      console.log(`이미지 템플릿 생성 성공: ${JSON.stringify(body, null, 2)}`)
      const templateCode = body.data.templateCode
      console.log(`생성된 이미지 템플릿 코드: ${templateCode}`)

      const recipients = [
        {
          phone: KKO_MOBILE_TO.to,
          ...(KKO_MOBILE_TO.variables
            ? { variables: KKO_MOBILE_TO.variables }
            : {}),
        },
      ]

      const sendResponse = await this.sendon.kakao.sendBrandMessage({
        sendProfileId: KKO_SEND_PROFILE_ID,
        templateCode,
        to: recipients,
      })

      if (sendResponse.code === HttpStatusCode.Ok) {
        console.log(`이미지 템플릿 발송 성공: ${JSON.stringify(sendResponse, null, 2)}`)
      } else {
        console.log(`이미지 템플릿 발송 실패: ${JSON.stringify(sendResponse, null, 2)}`)
      }
    } catch (error) {
      const err = error as SdoError
      console.error(`에러 발생: ${err.message}`)
      if (err.response?.data) {
        console.error(`응답 본문: ${JSON.stringify(err.response.data, null, 2)}`)
      }
    }
  }
}
