package io.sendon.example.kakao;

import io.sendon.example.BaseScenario;

public class ZKakaoDivider extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    System.out.println("Nothing to do");
  }

  @Override
  public String getDescription() {
    return "Kakao";
  }

  @Override
  public boolean isDivider() {
    return true;
  }
}
