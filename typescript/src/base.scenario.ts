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
  to: process.env.KKO_MOBILE_TO || 'YOUR_TO',
  variables: {
    '#{고객명}': '홍길동',
  },
}
export const KKO_TEMPLATE_ID = process.env.KKO_TEMPLATE_ID || "YOUR_TEMPLATE_ID"
export const KKO_CHANNEL_ID = process.env.KKO_CHANNEL_ID || "YOUR_CHANNEL_ID"
export const KKO_TOKEN_YOU_RECEIVED = process.env.KKO_TOKEN_YOU_RECEIVED || "TOKEN_YOU_RECEIVED"
export const KKO_SEND_PROFILE_ID = process.env.KKO_SEND_PROFILE_ID || "YOUR_SEND_PROFILE_ID"
export const KKO_CHANNEL_PHONE_NUMBER = process.env.KKO_CHANNEL_PHONE_NUMBER || "YOUR_CHANNEL_PHONENUMBER"
export const KKO_BRAND_MESSAGE_TEMPLATE_CODE = process.env.KKO_BRAND_MESSAGE_TEMPLATE_CODE || "YOUR_BRAND_MESSAGE_TEMPLATE_CODE"

export const RCS_MOBILE_TO = process.env.RCS_MOBILE_TO ? process.env.RCS_MOBILE_TO.split(',') : ["YOUR_TO"]
export const RCS_MOBILE_FROM = process.env.RCS_MOBILE_FROM || "YOUR_FROM"
export const RCS_CHATBOT_ID = process.env.RCS_CHATBOT_ID || "YOUR_CHATBOT_ID"
export const RCS_MESSAGEBASE_ID = process.env.RCS_MESSAGEBASE_ID || "YOUR_MESSAGEBASE_ID"
export const RCS_BRAND_ID = process.env.RCS_BRAND_ID || "YOUR_BRAND_ID"
export const RCS_BRAND_KEY = process.env.RCS_BRAND_KEY || "YOUR_BRAND_KEY"
export const RCS_AGENCY_ID = process.env.RCS_AGENCY_ID || "aligobiz"
export const RCS_AGENCY_KEY = process.env.RCS_AGENCY_KEY || "YOUR_AGENCY_KEY"
export const RCS_CLIENT_ID = process.env.RCS_CLIENT_ID || "YOUR_CLIENT_ID"
export const RCS_CLIENT_SECRET = process.env.RCS_CLIENT_SECRET || "YOUR_CLIENT_SECRET"

export const CONTACTS_PHONENUMBER_TO_BLOCK = process.env.CONTACTS_PHONENUMBER_TO_BLOCK || "YOUR_PHONENUMBER_TO_BLOCK"
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

  sleep(ms: number, signal?: AbortSignal): Promise<void> {
    return new Promise((resolve, reject) => {
      if (signal?.aborted) return reject(new Error('Sleep aborted'));

      let seconds = 0;
      let dots = '';
      const interval = setInterval(() => {
        seconds++;
        dots += '😎';
        process.stdout.write(`\r[${seconds}]${dots}`);
      }, 1000);

      const timeout = setTimeout(() => {
        clearInterval(interval);
        process.stdout.write('\n');
        resolve();
      }, ms);

      if (signal) {
        signal.addEventListener("abort", () => {
          clearTimeout(timeout);
          clearInterval(interval);
          reject(new Error());
        });
      }
    });
  }
}
