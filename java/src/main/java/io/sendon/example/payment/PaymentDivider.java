package io.sendon.example.payment;

import io.sendon.example.BaseScenario;

public class PaymentDivider extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    System.out.println("Nothing to do");
  }

  @Override
  public String getDescription() {
    return "Payment";
  }
  
  @Override
  public boolean isDivider() {
    return true;
  }
}
