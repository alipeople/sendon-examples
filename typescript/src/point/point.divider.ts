import { BaseScenario } from '../base.scenario'

export class PointDivider extends BaseScenario {
  description = 'Point'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}
