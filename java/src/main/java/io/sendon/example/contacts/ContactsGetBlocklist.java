package io.sendon.example.contacts;

import io.sendon.Log;
import io.sendon.contacts.response.GetBlocklist;
import io.sendon.example.BaseScenario;

public class ContactsGetBlocklist extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    // 기본 차단목록 조회
    GetBlocklist basicBlocklist = sendon.contacts.getBlocklist(0, 10);
    Log.d("차단목록 조회: " + gson.toJson(basicBlocklist));
    
    // 발신번호로 필터링
    if (SMS_MOBILE_FROM != null && !SMS_MOBILE_FROM.isEmpty()) {
      GetBlocklist senderFiltered = sendon.contacts.getBlocklist(
          SMS_MOBILE_FROM,
          null,
          null,
          null,
          null,
          0,
          5
      );
      Log.d("발신번호 필터링: " + gson.toJson(senderFiltered));
    }
    
    // 카카오 채널 ID로 필터링
    if (KKO_CHANNEL_ID != null && !KKO_CHANNEL_ID.isEmpty()) {
      GetBlocklist kakaoFiltered = sendon.contacts.getBlocklist(
          null,
          new String[]{KKO_CHANNEL_ID},
          null,
          null,
          null,
          0,
          5
      );
      Log.d("카카오 채널 필터링: " + gson.toJson(kakaoFiltered));
    }
    
    // 차단 타입별 필터링
    GetBlocklist typeFiltered = sendon.contacts.getBlocklist(
        null,
        null,
        new String[]{"API"},
        null,
        null,
        0,
        10
    );
    Log.d("차단 타입 필터링: " + gson.toJson(typeFiltered));
    
    // 날짜 범위로 필터링
    String endDate = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String startDate = java.time.LocalDateTime.now().minusDays(30).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    GetBlocklist dateFiltered = sendon.contacts.getBlocklist(
        null,
        null,
        null,
        startDate,
        endDate,
        0,
        20
    );
    Log.d("날짜 범위 필터링: " + gson.toJson(dateFiltered));
  }

  @Override
  public String getDescription() {
    return "[주소록] 차단목록 조회";
  }


}
