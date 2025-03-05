package io.sendon.example;

import io.github.cdimascio.dotenv.Dotenv;
import io.sendon.ApiException;
import io.sendon.Sendon;

public abstract class BaseScenario {
  private static final Dotenv dotenv = Dotenv.load();
  
  protected String USER_ID;
  protected String USER_APIKEY;
  protected String SMS_MOBILE_TO;
  protected String SMS_MOBILE_FROM;
  protected String KKO_MOBILE_TO;
  protected String KKO_TEMPLATE_ID;
  protected String KKO_CHANNEL_ID;
  protected String KKO_TOKEN_YOU_RECEIVED;
  protected String KKO_SEND_PROFILE_ID;
  protected String KKO_CHANNEL_PHONE_NUMBER;

  public abstract void execute() throws InterruptedException;
  public abstract String getDescription();

  protected Sendon sendon;

  public BaseScenario() {
    USER_ID = dotenv.get("USER_ID");
    USER_APIKEY = dotenv.get("USER_APIKEY");
    SMS_MOBILE_TO = dotenv.get("SMS_MOBILE_TO");
    SMS_MOBILE_FROM = dotenv.get("SMS_MOBILE_FROM");
    KKO_MOBILE_TO = dotenv.get("KKO_MOBILE_TO");
    KKO_TEMPLATE_ID = dotenv.get("KKO_TEMPLATE_ID");
    KKO_CHANNEL_ID = dotenv.get("KKO_CHANNEL_ID");
    KKO_TOKEN_YOU_RECEIVED = dotenv.get("KKO_TOKEN_YOU_RECEIVED");
    KKO_SEND_PROFILE_ID = dotenv.get("KKO_SEND_PROFILE_ID");
    KKO_CHANNEL_PHONE_NUMBER = dotenv.get("KKO_CHANNEL_PHONE_NUMBER");

    sendon = Sendon.getInstance(USER_ID, USER_APIKEY);
  }

  protected void handleException(ApiException e) {
    System.err.println("에러 응답:");
    System.err.println("\tStatus code: " + e.getCode());
    System.err.println("\tResponse headers: " + e.getResponseHeaders());
    System.err.println("\tResponse body: " + e.getResponseBody());
  }

  protected static String generateRandomCode() {
    StringBuilder code = new StringBuilder();
    for (int i = 0; i < 3; i++) code.append((char) (Math.random() * 26 + 'a'));
    return code.toString();
  }

  public boolean isDivider() {
    return false;
  }

  public void sleep(long ms) {
    Thread thread = new Thread(() -> {
      int seconds = 0;
      StringBuilder dots = new StringBuilder();
      try {
        while (true) {
          Thread.sleep(1000);
          seconds++;
          dots.append("😎");
          System.out.print("\r[" + seconds + "]" + dots);
        }
      } catch (InterruptedException e) {
        // Thread interrupted, stop printing
      }
    });

    thread.start();

    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread.interrupt();
    System.out.print("\n");
  }
}
