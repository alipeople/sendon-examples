package io.sendon.example.rcs;

import io.sendon.example.BaseScenario;

public class ZRcsDivider extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    System.out.println("Nothing to do");
  }

  @Override
  public String getDescription() {
    return "RCS";
  }

  @Override
  public boolean isDivider() {
    return true;
  }
}