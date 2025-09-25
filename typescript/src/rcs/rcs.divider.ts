import { BaseScenario } from '../base.scenario'

export class RcsDivider extends BaseScenario {
  description = 'RCS'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}