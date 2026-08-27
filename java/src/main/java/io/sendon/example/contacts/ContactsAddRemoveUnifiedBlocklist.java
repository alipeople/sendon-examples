package io.sendon.example.contacts;

import io.sendon.Log;
import io.sendon.contacts.response.CreateBlocklistUnified;
import io.sendon.contacts.response.DeleteBlocklistUnified;
import io.sendon.contacts.response.GetBlocklistUnified;
import io.sendon.example.BaseScenario;

public class ContactsAddRemoveUnifiedBlocklist extends BaseScenario {

  /** 예제로 등록했다가 해제할 전화번호입니다. 실제로 차단할 번호로 바꿔서 사용하세요. */
  private static final String PHONE_NUMBER_TO_BLOCK = "01098765432";

  @Override
  public void execute() throws InterruptedException {
    // 통합 수신거부 추가
    // 발신번호별 차단(addBlocklist)과 달리 발신번호·채널과 무관하게 계정 전체에 적용됩니다.
    // messageType은 등록 사유를 남기는 값이며, 생략하면 DIRECT(발신정보 없이 직접 등록)입니다.
    CreateBlocklistUnified created = sendon.contacts.createBlocklistUnified(PHONE_NUMBER_TO_BLOCK);
    Log.d("통합 수신거부 추가: " + gson.toJson(created));

    // 방금 추가한 번호를 keyword로 조회
    GetBlocklistUnified found = sendon.contacts.getBlocklistUnified(
        null,
        null,
        PHONE_NUMBER_TO_BLOCK,
        null,
        null,
        0,
        10
    );
    Log.d("통합 수신거부 조회: " + gson.toJson(found));

    // 추가 응답으로 받은 id로 해제
    if (created != null && created.data != null) {
      DeleteBlocklistUnified deleted = sendon.contacts.deleteBlocklistUnified(created.data.id);
      Log.d("통합 수신거부 해제: " + gson.toJson(deleted));
    }
  }

  @Override
  public String getDescription() {
    return "[주소록] 통합 수신거부 추가조회삭제";
  }


}
