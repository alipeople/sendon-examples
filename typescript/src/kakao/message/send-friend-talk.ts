/* eslint-disable no-console */
import { SdoError } from '@alipeople/sendon-sdk-typescript'
import { SdoKakaoSendFriendTalkResponse } from '@alipeople/sendon-sdk-typescript/dist/wrapper/kakao/kakao.wrapper'
import { outdent } from 'outdent'

import { BaseScenario, KKO_MOBILE_TO, KKO_SEND_PROFILE_ID } from '../../base.scenario'

export class SendFriendTalk extends BaseScenario {
  description = '[카카오] 친구톡 발송'

  async execute() {
    try {
      const result: SdoKakaoSendFriendTalkResponse = await this.sendon.kakao.sendFriendTalk(KKO_SEND_PROFILE_ID, {
        recipients: [KKO_MOBILE_TO],
        message: outdent`
          안녕하세요. #{이름}님.
          SDK를 이용한 친구톡 발송 테스트입니다.
        `,
        isAd: false,
        messageType: 'FRIEND_TALK',
        buttons: [
          {
            type: 'WL',
            name: '웹사이트 바로가기',
            urlMobile: 'https://sendon.io',
          },
        ]
      })
      console.log(`응답: ${JSON.stringify(result, null, 2)}`)
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
