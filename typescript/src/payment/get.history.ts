/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { GetPaymentHistoriesResponse, GetPaymentHistoryResponse, SdoError } from '@alipeople/sendon-sdk-typescript';

import { BaseScenario } from '../base.scenario';

export class GetHistory extends BaseScenario {
  description = '[결제] 결제 내역 조회'

  async execute() {
    try {
      const paymentHistories: GetPaymentHistoriesResponse = await this.sendon.payment.getPaymentHistories({
        page: 1,
        limit: 10,
        startDate: new Date('2025-01-01 00:00:00'),
        endDate: new Date('2025-01-31 00:00:00'),
      })
      console.log(`응답: ${JSON.stringify(paymentHistories, null, 2)}`)

      if (paymentHistories?.data.histories.length > 0) {
        const historyId = paymentHistories.data.histories[0].id
        const paymentHistory: GetPaymentHistoryResponse = await this.sendon.payment.getPaymentHistoryDetail(Number(historyId))
        console.log(`응답: ${JSON.stringify(paymentHistory, null, 2)}`)
      }
    } catch (error) {
      const response = (error as SdoError).response?.data
      console.log(`에러 응답: ${JSON.stringify(response, null, 2)}`)
    }
  }
}
