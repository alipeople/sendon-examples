/* eslint-disable no-console */


import { HttpStatusCode } from 'axios'
import { BaseScenario, KKO_SEND_PROFILE_ID } from '../../base.scenario'

export class CreateTemplate extends BaseScenario {
  description = '[카카오] 알림톡 템플릿 생성'

  async execute() {
    const result1 = await this.sendon.kakao.createTemplate(KKO_SEND_PROFILE_ID, {
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

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
