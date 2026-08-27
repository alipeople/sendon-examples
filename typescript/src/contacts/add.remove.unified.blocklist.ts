/* eslint-disable no-console */
import {
  CreateBlocklistUnifiedResponseDto,
  DeleteBlocklistUnifiedResponseDto,
  GetBlocklistUnifiedResponseDto,
  SdoError,
} from "@alipeople/sendon-sdk-typescript";

import { HttpStatusCode } from "axios";
import { BaseScenario, CONTACTS_PHONENUMBER_TO_BLOCK } from "../base.scenario";

export class AddRemoveUnifiedBlocklist extends BaseScenario {
  description = "[주소록] 통합 수신거부 추가조회삭제";

  async execute() {
    try {
      // 통합 수신거부 추가
      // 발신번호별 차단(createBlocklist)과 달리 발신번호·채널과 무관하게 계정 전체에 적용됩니다.
      // messageType은 등록 사유를 남기는 값이며, 생략하면 DIRECT(발신정보 없이 직접 등록)입니다.
      const created: CreateBlocklistUnifiedResponseDto =
        await this.sendon.contacts.createBlocklistUnified({
          phoneNumber: CONTACTS_PHONENUMBER_TO_BLOCK,
        });
      console.log("통합 수신거부 추가:", JSON.stringify(created, null, 2));

      // 방금 추가한 번호를 keyword로 조회
      const found: GetBlocklistUnifiedResponseDto =
        await this.sendon.contacts.getBlocklistUnified({
          keyword: CONTACTS_PHONENUMBER_TO_BLOCK,
          cursor: 0,
          limit: 10,
        });
      console.log("통합 수신거부 조회:", JSON.stringify(found, null, 2));

      // 추가 응답으로 받은 id로 해제
      if (created.code === HttpStatusCode.Ok && created.data) {
        const deleted: DeleteBlocklistUnifiedResponseDto =
          await this.sendon.contacts.deleteBlocklistUnified(created.data.id);
        console.log("통합 수신거부 해제:", JSON.stringify(deleted, null, 2));
      }
    } catch (error) {
      const err = error as SdoError;
      console.error(`에러 발생: ${err.message}`);
      if (err.response?.data) {
        console.error(
          `응답 본문: ${JSON.stringify(err.response.data, null, 2)}`
        );
      }
    }
  }
}
