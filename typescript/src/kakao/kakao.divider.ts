import { BaseScenario } from '../base.scenario'

export class KakaoDivider extends BaseScenario {
  description = 'Kakao'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}
