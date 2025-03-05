/* eslint-disable no-console */

import { SdoError } from '@alipeople/sendon-sdk-typescript'

import { BaseScenario, KKO_SEND_PROFILE_ID } from '../../base.scenario'

export class CreateTemplate extends BaseScenario {
  description = '[카카오] 템플릿 생성'

  async execute() {
    try {
      const result = await this.sendon.kakao.createTemplate(KKO_SEND_PROFILE_ID, {
        templateName: '테스트 템플릿',
        templateContent: '안녕하세요 #{이름}님, 테스트 템플릿입니다.',
        buttons: [
          {
            type: 'WL',
            name: '웹링크',
            ordering: 1,
            urlMobile: 'https://example.com',
            urlPc: 'https://example.com',
          },
        ],
      })
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
