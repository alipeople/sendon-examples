# Java로 작성된 Sendon 예제

## 사전 준비 사항

- **Java Development Kit (JDK)**: 버전 17 이상
  - [OpenJDK 설치](https://openjdk.org/install/) 또는 선호하는 JDK 배포판 사용.
- **Gradle**: 저장소에 `gradlew`로 포함되어 있어 별도의 설치가 필요 없습니다.
- **환경 변수 파일 (.env)**: API 키와 같은 중요한 설정을 포함하는 `.env` 파일을 준비합니다. 예시:

  ```plaintext
  USER_ID=YOUR_ID
  USER_APIKEY=YOUR_APIKEY
  SMS_MOBILE_TO=YOUR_TO
  SMS_MOBILE_FROM=YOUR_FROM
  KKO_MOBILE_TO=YOUR_TO
  KKO_TEMPLATE_ID=YOUR_TEMPLATE_ID
  KKO_CHANNEL_ID=YOUR_CHANNEL_ID
  KKO_TOKEN_YOU_RECEIVED=YOUR_TOKEN
  KKO_SEND_PROFILE_ID=YOUR_SEND_PROFILE_ID
  KKO_CHANNEL_PHONE_NUMBER=YOUR_KAKAO_PHONE_NUMBER
  ```

## 빌드 방법

```bash
❯ ./gradlew build
```

## 실행 방법

```bash
❯ java -jar build/libs/sendon-java-example-all.jar

=========== SMS ===========
1. [SMS] 즉시문자 발송 (SmsSendShortMessageNowScenario)
2. [SMS] 예약문자 발송 (SmsSendShortMessageScheduleScenario)
3. [SMS] 발송문자 조회 (SmsQueryShortMessageScenario)
4. [SMS] 예약문자 취소 (SmsCancelShortMessageScenario)
5. [LMS] 즉시문자 발송 (SmsSendLongMessageNowScenario)
6. [LMS] 예약문자 발송 (SmsSendLongMessageScheduleScenario)
7. [LMS] 발송문자 조회 (SmsQueryLongMessageScenario)
8. [LMS] 예약문자 취소 (SmsCancelLongMessageScenario)
9. [MMS] 이미지 업로드 및 발송 (SmsSendLongMessageWithUploadImageScenario)

=========== Kakao ===========
10. [카카오] 브랜드메시지 텍스트 템플릿 생성 및 발송 (KakaoCreateBrandMessageTemplate)
11. [카카오] 브랜드메시지 이미지 템플릿 생성 (KakaoCreateBrandMessageImageTemplate)
12. [카카오] 브랜드메시지 와이드 템플릿 생성 (KakaoCreateBrandMessageWideTemplate)
13. [카카오] 브랜드메시지 와이드 아이템리스트 템플릿 생성 (KakaoCreateBrandMessageWideItemListTemplate)
14. [카카오] 브랜드메시지 캐러셀 템플릿 생성 (KakaoCreateBrandMessageCarouselTemplate)
15. [카카오] 브랜드메시지 템플릿 목록 조회 (KakaoGetBrandMessageTemplates)
16. [카카오] 브랜드메시지 템플릿 상세 조회 (KakaoGetBrandMessageTemplate)
17. [카카오] 브랜드메시지 텍스트 템플릿 수정 (KakaoUpdateBrandMessageTemplate)
18. [카카오] 브랜드메시지 템플릿 삭제 (KakaoDeleteBrandMessageTemplate)
19. [카카오] 브랜드메시지 발송 (KakaoSendBrandMessage)
20. [카카오] 브랜드메시지 이미지 업로드 (KakaoUploadBrandMessageImage)
21. [카카오] 브랜드메시지 와이드 이미지 업로드 (KakaoUploadBrandMessageWideImage)
22. [카카오] 채널 인증번호 요청 (KakaoRequestVerification)
23. [카카오] 채널 등록 (KakaoRegisterProfile)
24. [카카오] 발신프로필 리스트 조회 (KakaoGetSendProfiles)
25. [카카오] 템플릿 목록 조회 (KakaoGetTemplateList)
26. [카카오] 템플릿 조회 (KakaoGetTemplate)
27. [카카오] 템플릿 생성 (KakaoCreateTemplate)
28. [카카오] 알림톡 발송 (KakaoSendAlimTalk)
29. [카카오] 대체문자 이미지 업로드 (KakaoUploadFallbackImage)

=========== RCS ===========
30. [RCS] 즉시 RCS 메시지 발송 (RcsSendMessageNowScenario)
31. [RCS] 발송한 RCS 메시지 조회 (RcsQueryMessageScenario)
32. [RCS] CSV 업로드용 Presigned URL 조회 (RcsQueryPresignedUrlScenario)

=========== Sender ===========
33. [발신번호] 발신번호 목록조회 (SenderGetNumbers)

=========== Point ===========
34. [포인트] 단가 조회 (PointGetCost)
35. [포인트] 포인트 조회 (PointGetPoint)

=========== Contacts ===========
36. [주소록] 차단목록 조회 (ContactsGetBlocklist)
37. [주소록] 수신거부 추가삭제 (ContactsAddRemoveBlocklist)

=========== Payment ===========
38. [결제] 결제 내역 조회 (PaymentGetHistories)
0. Exit
Select a command:
```

선택 가능한 메뉴 옆에 클래스명이 표기되어 있습니다. 선택된 기능의 코드 구현부가 궁금하면 클래스 구현을 참조하면 됩니다.<br>
Sendon SDK나 API에 대해 궁금한 점이 있으시면 언제든지 [저희에게](mailto:dev@alipeople.kr) 문의해 주세요.
