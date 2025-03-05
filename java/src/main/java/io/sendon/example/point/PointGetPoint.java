package io.sendon.example.point;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.PointsListResponseDto;

public class PointGetPoint extends BaseScenario {
  
  @Override
  public void execute() throws InterruptedException {
    try {
      PointsListResponseDto response = sendon.getPoint().listPoints();
      System.out.println("응답:\n\t" + response.toJson());
    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[포인트] 포인트 조회";
  }
  
}
