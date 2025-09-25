/* eslint-disable no-console */
import { SdoRcsCreateCsvUploadUrlResponseData } from '@alipeople/sendon-sdk-typescript/dist/wrapper/rcs/rcs.wrapper'

import { HttpStatusCode } from 'axios'
import { BaseScenario } from '../base.scenario'

export class QueryPresignedUrl extends BaseScenario {
  description = '[RCS] CSV 업로드용 Presigned URL 조회'

  async execute() {
    const result1: SdoRcsCreateCsvUploadUrlResponseData = await this.sendon.rcs.createCsvUploadUrl()

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
      console.log(`업로드 URL: ${result1.data?.presignedUrl}`)
      console.log(`업로드 URL: ${result1.data?.bucket}`)
      console.log(`파일 키: ${result1.data?.key}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}