package io.sendon.example.contacts;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.CreateBlocklistRequestDto;
import io.sendon.model.CreateBlocklistResponseDto;
import io.sendon.model.DeleteBlocklistResponseDto;

public class ContactsAddRemoveBlocklist extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      CreateBlocklistRequestDto dto = new CreateBlocklistRequestDto();
      dto.setPhoneNumber("01912345678");
      
      CreateBlocklistResponseDto response1 = sendon.getContacts().createBlocklist(dto);
      System.out.println("응답:\n\t" + response1.toJson());
      
      DeleteBlocklistResponseDto response2 = sendon.getContacts().deleteBlocklist(response1.getData().getId());
      System.out.println("응답:\n\t" + response2.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[주소록] 수신거부 추가삭제";
  }
  
  
}
