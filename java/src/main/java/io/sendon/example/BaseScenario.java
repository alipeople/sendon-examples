package io.sendon.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.cdimascio.dotenv.Dotenv;
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
  protected String KKO_BRAND_MESSAGE_TEMPLATE_CODE;
  protected String RCS_MOBILE_TO;
  protected String RCS_MOBILE_FROM;
  protected String RCS_CHATBOT_ID;
  protected String RCS_MESSAGEBASE_ID;
  protected String RCS_BRAND_ID;
  protected String RCS_BRAND_KEY;
  protected String RCS_AGENCY_ID;
  protected String RCS_AGENCY_KEY;
  protected String RCS_CLIENT_ID;
  protected String RCS_CLIENT_SECRET;

  public abstract void execute() throws InterruptedException;
  public abstract String getDescription();

  protected Sendon sendon;
  protected Gson gson;

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
    KKO_BRAND_MESSAGE_TEMPLATE_CODE = dotenv.get("KKO_BRAND_MESSAGE_TEMPLATE_CODE");
    RCS_MOBILE_TO = dotenv.get("RCS_MOBILE_TO");
    RCS_MOBILE_FROM = dotenv.get("RCS_MOBILE_FROM");
    RCS_CHATBOT_ID = dotenv.get("RCS_CHATBOT_ID");
    RCS_MESSAGEBASE_ID = dotenv.get("RCS_MESSAGEBASE_ID");
    RCS_BRAND_ID = dotenv.get("RCS_BRAND_ID");
    RCS_BRAND_KEY = dotenv.get("RCS_BRAND_KEY");
    RCS_AGENCY_ID = dotenv.get("RCS_AGENCY_ID");
    RCS_AGENCY_KEY = dotenv.get("RCS_AGENCY_KEY");
    RCS_CLIENT_ID = dotenv.get("RCS_CLIENT_ID");
    RCS_CLIENT_SECRET = dotenv.get("RCS_CLIENT_SECRET");

    sendon = Sendon.getInstance(USER_ID, USER_APIKEY, true);

    gson = new GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create();
  }

  protected void handleException(Exception e) {
    System.err.println("에러 응답:");
    e.printStackTrace();
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
