package io.sendon.example.sender;

import java.math.BigDecimal;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.ListUserNumbersResponseDto;

public class SenderGetNumbers extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      ListUserNumbersResponseDto response = sendon.getSender().listUserNumbers(BigDecimal.ZERO, BigDecimal.TEN);
      System.out.println("응답:\n\t" + response.toJson());
      
    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[발신번호] 발신번호 목록조회";
  }
}
