package io.sendon.example.contacts;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.GetBlocklistResponseDto;

public class ContactsGetBlocklist extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      GetBlocklistResponseDto response = sendon.getContacts().getBlocklist(null, null);
      System.out.println("응답:\n\t" + response.toJson());
      
    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[주소록] 차단목록 조회";
  }
  
  
}
