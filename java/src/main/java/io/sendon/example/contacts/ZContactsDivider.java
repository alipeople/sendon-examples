package io.sendon.example.contacts;

import io.sendon.example.BaseScenario;

public class ZContactsDivider extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    System.out.println("Nothing to do");
  }

  @Override
  public String getDescription() {
    return "Contacts";
  }

  @Override
  public boolean isDivider() {
    return true;
  }
}
