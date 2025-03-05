import { BaseScenario } from '../base.scenario'

export class PaymentDivider extends BaseScenario {
  description = 'Payment'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}
