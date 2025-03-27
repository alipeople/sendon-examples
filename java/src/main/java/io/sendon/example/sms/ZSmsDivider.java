package io.sendon.example.sms;

import io.sendon.example.BaseScenario;

public class ZSmsDivider extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    System.out.println("Nothing to do");
  }

  @Override
  public String getDescription() {
    return "SMS";
  }

  @Override
  public boolean isDivider() {
    return true;
  }
}
