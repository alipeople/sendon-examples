/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import {
  CreateBlocklistResponseDto,
  DeleteBlocklistResponseDto,
} from "@alipeople/sendon-sdk-typescript";

import { HttpStatusCode } from "axios";
import {
  BaseScenario,
  CONTACTS_PHONENUMBER_TO_BLOCK,
  KKO_CHANNEL_ID,
  SMS_MOBILE_FROM,
} from "../base.scenario";

export class AddRemoveBlocklist extends BaseScenario {
  description = "[주소록] 수신거부 추가삭제";

  async execute() {
    // 발신번호별 SMS 차단 추가
    const senderBlocklist: CreateBlocklistResponseDto =
      await this.sendon.contacts.createBlocklist({
        phoneNumber: CONTACTS_PHONENUMBER_TO_BLOCK,
        messageType: "SMS",
        senderNumber: SMS_MOBILE_FROM,
      });
    console.log(
      "발신번호별 차단 추가:",
      JSON.stringify(senderBlocklist, null, 2)
    );

    // 카카오 채널 차단 추가
    if (KKO_CHANNEL_ID && KKO_CHANNEL_ID !== "YOUR_CHANNEL_ID") {
      const kakaoBlocklist: CreateBlocklistResponseDto =
        await this.sendon.contacts.createBlocklist({
          phoneNumber: CONTACTS_PHONENUMBER_TO_BLOCK,
          messageType: "KAKAO",
          kakaoChannelId: KKO_CHANNEL_ID,
        });
      console.log(
        "카카오 채널 차단 추가:",
        JSON.stringify(kakaoBlocklist, null, 2)
      );
    }

    await this.sleep(2000);

    // 차단 목록 삭제
    if (senderBlocklist.code === HttpStatusCode.Ok && senderBlocklist.data) {
      const deleteResult: DeleteBlocklistResponseDto =
        await this.sendon.contacts.deleteBlocklist(senderBlocklist.data.id);
      console.log(
        "발신번호별 차단 삭제:",
        JSON.stringify(deleteResult, null, 2)
      );
    }
  }
}
