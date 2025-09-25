/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { GetBlocklistResponseDto } from "@alipeople/sendon-sdk-typescript";

import {
    BaseScenario,
    KKO_CHANNEL_ID,
    SMS_MOBILE_FROM,
} from "../base.scenario";

export class GetBlocklist extends BaseScenario {
  description = "[주소록] 차단목록 조회";

  async execute() {
    // 기본 차단목록 조회
    const basicBlocklist: GetBlocklistResponseDto =
      await this.sendon.contacts.getBlocklist({
        cursor: 0,
        limit: 10,
      });
    console.log("차단목록 조회:", JSON.stringify(basicBlocklist, null, 2));

    // 발신번호로 필터링
    if (SMS_MOBILE_FROM && SMS_MOBILE_FROM !== "YOUR_FROM") {
      const senderFiltered: GetBlocklistResponseDto =
        await this.sendon.contacts.getBlocklist({
          senderNumber: SMS_MOBILE_FROM,
          cursor: 0,
          limit: 5,
        });
      console.log("발신번호 필터링:", JSON.stringify(senderFiltered, null, 2));
    }

    // 카카오 채널 ID로 필터링
    if (KKO_CHANNEL_ID && KKO_CHANNEL_ID !== "YOUR_CHANNEL_ID") {
      const kakaoFiltered: GetBlocklistResponseDto =
        await this.sendon.contacts.getBlocklist({
          kakaoChannelIds: [KKO_CHANNEL_ID],
          cursor: 0,
          limit: 5,
        });
      console.log(
        "카카오 채널 필터링:",
        JSON.stringify(kakaoFiltered, null, 2)
      );
    }

    // 차단 타입별 필터링
    const typeFiltered: GetBlocklistResponseDto =
      await this.sendon.contacts.getBlocklist({
        blockType: ["API"],
        cursor: 0,
        limit: 10,
      });
    console.log("차단 타입 필터링:", JSON.stringify(typeFiltered, null, 2));

    // 날짜 범위로 필터링
    const now = new Date();
    const thirtyDaysAgo = new Date(now.getTime() - 30 * 24 * 60 * 60 * 1000);

    const dateFiltered: GetBlocklistResponseDto =
      await this.sendon.contacts.getBlocklist({
        startDate: thirtyDaysAgo.toISOString().slice(0, 19).replace("T", " "),
        endDate: now.toISOString().slice(0, 19).replace("T", " "),
        cursor: 0,
        limit: 20,
      });
    console.log("날짜 범위 필터링:", JSON.stringify(dateFiltered, null, 2));
  }
}
