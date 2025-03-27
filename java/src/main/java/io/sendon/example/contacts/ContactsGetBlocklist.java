package io.sendon.example.contacts;

import io.sendon.Log;
import io.sendon.contacts.response.GetBlocklist;
import io.sendon.example.BaseScenario;

public class ContactsGetBlocklist extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
      GetBlocklist getBlocklist = sendon.contacts.getBlocklist(0, 10);
      Log.d("GetBlocklist: " + gson.toJson(getBlocklist));
  }

  @Override
  public String getDescription() {
    return "[주소록] 차단목록 조회";
  }


}
