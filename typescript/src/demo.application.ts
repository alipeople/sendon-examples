/* eslint-disable no-console */
import * as readline from "readline";

import { BaseScenario } from "./base.scenario";
import { AddRemoveBlocklist } from "./contacts/add.remove.blocklist";
import { ContactsDivider } from "./contacts/contacts.divider";
import { GetBlocklist } from "./contacts/get.blocklist";
import { RegisterChannel } from "./kakao/channel/register-channel";
import { RequestChannelVerification } from "./kakao/channel/request-verification";

import { GetSendProfiles } from "./kakao/channel/get-send-profiles";
import { UploadFallbackImage } from "./kakao/assets/upload-fallback-image";
import { KakaoDivider } from "./kakao/kakao.divider";
import { SendBrandMessage } from "./kakao/brand-message/send-brand-message";
import { CreateBrandMessageTemplate } from "./kakao/brand-message/create-brand-message-template";
import { CreateImageBrandMessageTemplate } from "./kakao/brand-message/create-image-brand-message-template";
import { CreateWideBrandMessageTemplate } from "./kakao/brand-message/create-wide-brand-message-template";
import { GetBrandMessageTemplate } from "./kakao/brand-message/get-brand-message-template";
import { GetBrandMessageTemplateList } from "./kakao/brand-message/get-brand-message-templates";
import { UpdateBrandMessageTemplate } from "./kakao/brand-message/update-brand-message-template";
import { DeleteBrandMessageTemplate } from "./kakao/brand-message/delete-brand-message-template";
import { SendAlimTalk } from "./kakao/message/send-alim-talk";
import { CreateTemplate } from "./kakao/template/create-template";
import { GetTemplate } from "./kakao/template/get-template";
import { GetTemplateList } from "./kakao/template/get-template-list";
import { GetHistory } from "./payment/get.history";
import { PaymentDivider } from "./payment/payment.divider";
import { GetCost } from "./point/get.cost";
import { GetPoint } from "./point/get.point";
import { PointDivider } from "./point/point.divider";
import { QueryRcsMessage } from "./rcs/query.message";
import { QueryPresignedUrl } from "./rcs/query.presigned.url";
import { RcsDivider } from "./rcs/rcs.divider";
import { SendRcsMessageNow } from "./rcs/send.message.now";
import { GetNumbers } from "./sender/get.sender";
import { SenderDivider } from "./sender/sender.divider";
import { CancelLongMessage } from "./sms/long.message/cancel.message";
import { QueryLongMessage } from "./sms/long.message/query.message";
import { SendLongMessageNow } from "./sms/long.message/send.message.now";
import { SendLongMessageSchedule } from "./sms/long.message/send.message.schedule";
import { UploadLongMessageImage } from "./sms/long.message/upload.image";
import { CancelShortMessage } from "./sms/short.message/cancel.message";
import { QueryShortMessage } from "./sms/short.message/query.message";
import { SendShortMessageNow } from "./sms/short.message/send.message.now";
import { SendShortMessageSchedule } from "./sms/short.message/send.message.schedule";
import { SmsDivider } from "./sms/sms.divider";

const scenarios: BaseScenario[] = [
  new SmsDivider(),
  new SendShortMessageNow(),
  new SendShortMessageSchedule(),
  new CancelShortMessage(),
  new QueryShortMessage(),
  new SendLongMessageNow(),
  new SendLongMessageSchedule(),
  new CancelLongMessage(),
  new QueryLongMessage(),
  new UploadLongMessageImage(),

  // [kakao]
  new KakaoDivider(),
  // [kakao] channel
  new RequestChannelVerification(),
  new RegisterChannel(),
  new GetSendProfiles(),
  // [kakao] alimtalk
  new SendAlimTalk(),
  new GetTemplateList(),
  new GetTemplate(),
  new CreateTemplate(),
  // [kakao] brand-message
  new SendBrandMessage(),
  new CreateBrandMessageTemplate(),
  new CreateImageBrandMessageTemplate(),
  new CreateWideBrandMessageTemplate(),
  new GetBrandMessageTemplateList(),
  new GetBrandMessageTemplate(),
  new UpdateBrandMessageTemplate(),
  new DeleteBrandMessageTemplate(),
  // [kakao] image
  new UploadFallbackImage(),

  // [rcs]
  new RcsDivider(),
  new SendRcsMessageNow(),
  new QueryRcsMessage(),
  new QueryPresignedUrl(),

  new SenderDivider(),
  new GetNumbers(),

  new PointDivider(),
  new GetCost(),
  new GetPoint(),

  new ContactsDivider(),
  new AddRemoveBlocklist(),
  new GetBlocklist(),

  new PaymentDivider(),
  new GetHistory(),
];

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

function showMenu() {
  let menuNumber = 1;
  scenarios.forEach((scenario) => {
    if (scenario.isDivider()) {
      console.log(`\n\t=========== ${scenario.description} ===========`);
    } else {
      console.log(
        `\t${menuNumber}. ${scenario.description} (${scenario.constructor.name})`
      );
      menuNumber++;
    }
  });
  console.log("\t0. Exit");
}

async function handleUserInput(input: string) {
  const choice = parseInt(input, 10);
  if (choice === 0) {
    console.log("Exiting...");
    rl.close();
    return;
  }

  const executableCommands = scenarios.filter(
    (scenario) => !scenario.isDivider()
  );
  if (choice > 0 && choice <= executableCommands.length) {
    await executableCommands[choice - 1].execute();
  } else {
    console.log("Invalid choice. Please try again.");
  }

  showMenu();
  rl.question("Select a command: ", handleUserInput);
}

showMenu();
rl.question("Select a command: ", handleUserInput);
