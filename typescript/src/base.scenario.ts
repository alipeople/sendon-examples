import { Sendon } from '@alipeople/sendon-sdk-typescript';
import dotenv from "dotenv";

dotenv.config();

/**
 * Please use configurations from .env 
 */
export const USER_ID = process.env.USER_ID || "YOUR_ID"
export const USER_APIKEY = process.env.USER_APIKEY || "YOUR_APIKEY"

export const SMS_MOBILE_TO = process.env.SMS_MOBILE_TO ? process.env.SMS_MOBILE_TO.split(',') : ["YOUR_TO"]
export const SMS_MOBILE_FROM = process.env.SMS_MOBILE_FROM || "YOUR_FROM"

export const KKO_MOBILE_TO = {
  to: process.env.KKO_MOBILE_TO || 'YOUR_TO01065331985',
  variables: {
    '#{고객명}': '홍길동',
  },
}
export const KKO_TEMPLATE_ID = process.env.KKO_TEMPLATE_ID || "YOUR_TEMPLATE_ID"
export const KKO_CHANNEL_ID = process.env.KKO_CHANNEL_ID || "YOUR_CHANNEL_ID"
export const KKO_TOKEN_YOU_RECEIVED = process.env.KKO_TOKEN_YOU_RECEIVED || "TOKEN_YOU_RECEIVED"
export const KKO_SEND_PROFILE_ID = process.env.KKO_SEND_PROFILE_ID || "YOUR_SEND_PROFILE_ID"
export const KKO_CHANNEL_PHONE_NUMBER = process.env.KKO_CHANNEL_PHONE_NUMBER || "YOUR_CHANNEL_PHONENUMBER"

export abstract class BaseScenario {
  description: string = ''
  abstract execute(): void

  protected sendon = new Sendon({
    id: USER_ID,
    apikey: USER_APIKEY,
    debug: false,
  })

  isDivider(): boolean {
    return false
  }

  sleep(ms: number): Promise<void> {
    return new Promise(resolve => {
      let seconds = 0;
      let dots = '';
      const interval = setInterval(() => {
        seconds++;
        dots += '😎';
        process.stdout.write(`\r[${seconds}]${dots}`);
      }, 1000);

      setTimeout(() => {
        clearInterval(interval);
        process.stdout.write('\n');
        resolve();
      }, ms);
    });
  }
}
