package io.sendon.example.point;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.point.response.GetPoints;

public class PointGetPoint extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    GetPoints getPoints = sendon.point.getPoints();
    Log.d("GetPoints: " + gson.toJson(getPoints));
  }

  @Override
  public String getDescription() {
    return "[포인트] 포인트 조회";
  }

}
