package io.sendon.example.payment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.GetPaymentHistoriesResponse;
import io.sendon.model.GetPaymentHistoryResponse;

public class PaymentGetHistory extends BaseScenario {

  @Override
  public void execute() throws InterruptedException {
    try {
      BigDecimal page = BigDecimal.ZERO;
      BigDecimal limit = BigDecimal.TEN;
      OffsetDateTime startDate = OffsetDateTime.now().minusDays(7);
      OffsetDateTime endDate = OffsetDateTime.now();

      GetPaymentHistoriesResponse response = sendon.getPayment().getPaymentHistories(page, limit, startDate, endDate);
      System.out.println("응답:\n\t" + response.toJson());

      if (!response.getData().getHistories().isEmpty()) {
        GetPaymentHistoryResponse historyResponse = sendon.getPayment().getPaymentHistory(response.getData().getHistories().get(0).getId());
        System.out.println("응답:\n\t" + historyResponse.toJson());
      }
    }catch(ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[결제] 결제 내역 조회";
  }
  
}
