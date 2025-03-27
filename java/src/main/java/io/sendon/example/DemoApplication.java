package io.sendon.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.sendon.example.contacts.ContactsAddRemoveBlocklist;
import io.sendon.example.contacts.ContactsGetBlocklist;
import io.sendon.example.contacts.ZContactsDivider;
import io.sendon.example.kakao.ZKakaoDivider;
import io.sendon.example.kakao.channel.KakaoGetSendProfiles;
import io.sendon.example.kakao.channel.KakaoRegisterProfile;
import io.sendon.example.kakao.channel.KakaoRequestVerification;
import io.sendon.example.kakao.image.KakaoUploadFallbackImage;
import io.sendon.example.kakao.image.KakaoUploadFriendImage;
import io.sendon.example.kakao.image.KakaoUploadFriendWideImage;
import io.sendon.example.kakao.message.KakaoSendAlimTalk;
import io.sendon.example.kakao.message.KakaoSendFriendTalk;
import io.sendon.example.kakao.template.KakaoCreateTemplate;
import io.sendon.example.kakao.template.KakaoGetTemplate;
import io.sendon.example.kakao.template.KakaoGetTemplateList;
import io.sendon.example.payment.PaymentDivider;
import io.sendon.example.payment.PaymentGetHistories;
import io.sendon.example.point.PointGetCost;
import io.sendon.example.point.PointGetPoint;
import io.sendon.example.point.ZPointDivider;
import io.sendon.example.sender.SenderGetNumbers;
import io.sendon.example.sender.ZSenderDivider;
import io.sendon.example.sms.ZSmsDivider;
import io.sendon.example.sms.longmessage.SmsCancelLongMessageScenario;
import io.sendon.example.sms.longmessage.SmsQueryLongMessageScenario;
import io.sendon.example.sms.longmessage.SmsSendLongMessageNowScenario;
import io.sendon.example.sms.longmessage.SmsSendLongMessageScheduleScenario;
import io.sendon.example.sms.longmessage.SmsSendLongMessageWithUploadImageScenario;
import io.sendon.example.sms.shortmessage.SmsCancelShortMessageScenario;
import io.sendon.example.sms.shortmessage.SmsQueryShortMessageScenario;
import io.sendon.example.sms.shortmessage.SmsSendShortMessageNowScenario;
import io.sendon.example.sms.shortmessage.SmsSendShortMessageScheduleScenario;

public class DemoApplication {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws InterruptedException {
        BaseScenario[] commands = {
            new ZSmsDivider(),
            new SmsSendShortMessageNowScenario(),
            new SmsSendShortMessageScheduleScenario(),
            new SmsQueryShortMessageScenario(),
            new SmsCancelShortMessageScenario(),
            new SmsSendLongMessageNowScenario(),
            new SmsSendLongMessageScheduleScenario(),
            new SmsQueryLongMessageScenario(),
            new SmsCancelLongMessageScenario(),
            new SmsSendLongMessageWithUploadImageScenario(),
            new ZKakaoDivider(),
            new KakaoRequestVerification(),
            new KakaoRegisterProfile(),
            new KakaoGetSendProfiles(),
            new KakaoGetTemplateList(),
            new KakaoGetTemplate(),
            new KakaoCreateTemplate(),
            new KakaoSendAlimTalk(),
            new KakaoSendFriendTalk(),
            new KakaoUploadFriendImage(),
            new KakaoUploadFriendWideImage(),
            new KakaoUploadFallbackImage(),
            new ZSenderDivider(),
            new SenderGetNumbers(),
            new ZPointDivider(),
            new PointGetCost(),
            new PointGetPoint(),
            new ZContactsDivider(),
            new ContactsGetBlocklist(),
            new ContactsAddRemoveBlocklist(),
            new PaymentDivider(),
            new PaymentGetHistories()
        };

        Scanner scanner = new Scanner(System.in);
        List<BaseScenario> executableCommands = new ArrayList<>();

        while (true) {
            executableCommands.clear();

            // 메뉴 항목 표시
            int menuNumber = 1;
            for (BaseScenario command : commands) {
                if (command.isDivider()) {
                    System.out.println("\n\t=========== " + command.getDescription() + " ===========");
                } else {
                    String className = command.getClass().getSimpleName();
                    System.out.println("\t" + menuNumber + ". " + command.getDescription() + " (" + className + ")");
                    executableCommands.add(command);
                    menuNumber++;
                }
            }

            System.out.println("\t0. Exit");
            System.out.print("Select a command: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            } else if (choice > 0 && choice <= executableCommands.size()) {
                // 선택된 커맨드 실행
                executableCommands.get(choice - 1).execute();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
