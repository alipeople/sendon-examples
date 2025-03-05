import { BaseScenario } from '../base.scenario'

export class ContactsDivider extends BaseScenario {
  description = 'Contacts'

  async execute() {}
  isDivider(): boolean {
    return true
  }
}
