import { BaseScenario } from '../base.scenario'

export class SmsDivider extends BaseScenario {
  description = 'SMS'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}
