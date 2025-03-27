/* eslint-disable no-console */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { GetPaymentHistoriesResponse, GetPaymentHistoryResponse, SdoError } from '@alipeople/sendon-sdk-typescript';

import { BaseScenario } from '../base.scenario';
import { HttpStatusCode } from 'axios';

export class GetHistory extends BaseScenario {
  description = '[결제] 결제 내역 조회'

  async execute() {
    const result1: GetPaymentHistoriesResponse = await this.sendon.payment.getPaymentHistories({
      page: 1,
      limit: 10,
      startDate: new Date('2025-01-01 00:00:00'),
      endDate: new Date('2099-04-31 00:00:00'),
    })

    if (result1.code === HttpStatusCode.Ok) {
      if (result1?.data.histories.length > 0) {
        const historyId = result1.data.histories[0].id
        const paymentHistory: GetPaymentHistoryResponse = await this.sendon.payment.getPaymentHistoryDetail(Number(historyId))
        console.log(`성공 응답: ${JSON.stringify(paymentHistory, null, 2)}`)
      }
    } else {
      console.log(`실패 응답: ${JSON.stringify(result1, null, 2)}`)
    }
  }
}
