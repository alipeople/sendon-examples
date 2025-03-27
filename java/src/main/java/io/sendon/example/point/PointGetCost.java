package io.sendon.example.point;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.point.response.GetCosts;

public class PointGetCost extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    GetCosts getCosts  = sendon.point.getCosts();
    Log.d("GetCosts: " + gson.toJson(getCosts));
  }

  @Override
  public String getDescription() {
    return "[포인트] 단가 조회";
  }

}
