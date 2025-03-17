/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'
import { SdoKakaoSendFriendTalkResponse } from '@alipeople/sendon-sdk-typescript/dist/wrapper/kakao/kakao.wrapper'
import { outdent } from 'outdent'

import { BaseScenario, KKO_MOBILE_TO, KKO_SEND_PROFILE_ID } from '../../base.scenario'
import { HttpStatusCode } from 'axios'

export class SendFriendTalk extends BaseScenario {
  description = '[카카오] 친구톡 발송'

  async execute() {
    const result1: SdoKakaoSendFriendTalkResponse = await this.sendon.kakao.sendFriendTalk({
      sendProfileId: KKO_SEND_PROFILE_ID,
      to: [KKO_MOBILE_TO.to],
      message: outdent`
        안녕하세요. #{이름}님.
        SDK를 이용한 친구톡 발송 테스트입니다.
      `,
      isAd: false,
      messageType: 'FT',
      buttons: [
        {
          type: 'WL',
          name: '웹사이트 바로가기',
          urlMobile: 'https://sendon.io',
        },
      ]
    })

    if (result1.code === HttpStatusCode.Ok) {
      console.log(`성공 응답: ${JSON.stringify(result1, null, 2)}`)
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
