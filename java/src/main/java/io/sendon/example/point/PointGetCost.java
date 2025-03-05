package io.sendon.example.point;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.CostGetResponseDto;

public class PointGetCost extends BaseScenario {
  
  @Override
  public void execute() throws InterruptedException {
    try {
      CostGetResponseDto response = sendon.getPoint().getCost();
      System.out.println("응답:\n\t" + response.toJson());
    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[포인트] 단가 조회";
  }
  
}
