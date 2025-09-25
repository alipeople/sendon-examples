package io.sendon.example.contacts;

import io.sendon.Log;
import io.sendon.contacts.response.AddBlocklist;
import io.sendon.contacts.response.DeleteBlocklist;
import io.sendon.example.BaseScenario;

public class ContactsAddRemoveBlocklist extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {    
    AddBlocklist senderBlocklist = sendon.contacts.addBlocklist(
        "01098765432", 
        "SMS", 
        SMS_MOBILE_FROM,
        null, 
        null, 
        null
    );
    Log.d("발신번호별 차단 추가: " + gson.toJson(senderBlocklist));
    
    if (senderBlocklist != null && senderBlocklist.data != null) {
      DeleteBlocklist deleteResult2 = sendon.contacts.deleteBlocklist(senderBlocklist.data.id);
      Log.d("발신번호별 차단 삭제: " + gson.toJson(deleteResult2));
    }
  }

  @Override
  public String getDescription() {
    return "[주소록] 수신거부 추가삭제";
  }


}
