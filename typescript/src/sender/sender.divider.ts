import { BaseScenario } from '../base.scenario'

export class SenderDivider extends BaseScenario {
  description = 'Sender'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}
