# TypeScript로 작성된 Sendon 예제

## 사전 준비 사항

- **Node.js**: >= v20
- **Yarn**: >= 1.22
- **환경 변수 파일 (.env)**: API 키와 같은 중요한 설정을 포함하는 `.env` 파일을 준비합니다. 예시:

  ```plaintext
  USER_ID=YOUR_ID
  USER_APIKEY="YOUR_APIKEY"
  SMS_MOBILE_TO="YOUR_TO"
  SMS_MOBILE_FROM="YOUR_FROM"

  KKO_MOBILE_TO="YOUR_TO"
  KKO_TEMPLATE_ID="YOUR_TEMPLATE_ID"
  KKO_CHANNEL_ID="YOUR_CHANNEL_ID"
  KKO_TOKEN_YOU_RECEIVED="YOUR_TOKEN"
  KKO_SEND_PROFILE_ID="YOUR_SEND_PROFILE_ID"
  KKO_CHANNEL_PHONE_NUMBER="YOUR_KAKAO_PHONE_NUMBER"
  ```

## 빌드 방법

```bash
❯ yarn install
❯ yarn build
```

## 실행 방법

```bash
❯ node dist/demo.application.js

=========== SMS ===========
1. [SMS] 즉시문자 발송 (SendShortMessageNow)
2. [SMS] 예약문자 발송 (SendShortMessageSchedule)
3. [SMS] 예약문자 취소 (CancelShortMessage)
4. [SMS] 발송문자 조회 (QueryShortMessage)
5. [LMS] 즉시문자 발송 (SendLongMessageNow)
6. [LMS] 예약문자 발송 (SendLongMessageSchedule)
7. [LMS] 예약문자 취소 (CancelLongMessage)
8. [LMS] 발송문자 조회 (QueryLongMessage)
9. [MMS] 이미지 업로드, 발송 (UploadLongMessageImage)

=========== Kakao ===========
10. [카카오] 채널 인증번호 요청 (RequestChannelVerification)
11. [카카오] 채널 등록 (RegisterChannel)
12. [카카오] 발신프로필 리스트 조회 (GetSendProfiles)
13. [카카오] 템플릿 목록 조회 (GetTemplateList)
14. [카카오] 템플릿 조회 (GetTemplate)
15. [카카오] 템플릿 생성 (CreateTemplate)
16. [카카오] 알림톡 발송 (SendAlimTalk)
17. [카카오] 친구톡 발송 (SendFriendTalk)
18. [카카오] 친구톡 이미지 업로드 (UploadFriendImage)
19. [카카오] 친구톡 와이드 이미지 업로드 (UploadFriendWideImage)
20. [카카오] 대체문자 이미지 업로드 (UploadFallbackImage)

=========== Sender ===========
21. [발신번호] 발신번호 목록조회 (GetNumbers)

=========== Point ===========
22. [포인트] 단가 조회 (GetCost)
23. [포인트] 포인트 조회 (GetPoint)

=========== Contacts ===========
24. [주소록] 수신거부 추가삭제 (AddRemoveBlocklist)
25. [주소록] 차단목록 조회 (GetBlocklist)

=========== Payment ===========
26. [결제] 결제 내역 조회 (GetHistory)
 0. Exit
Select a command:
```

선택 가능한 메뉴 옆에 클래스명이 표기되어 있습니다. 선택된 기능의 코드 구현부가 궁금하면 클래스 구현을 참조하면 됩니다.
Sendon SDK나 API에 대해 궁금한 점이 있으시면 언제든지 [저희에게](dev@alipeople.kr) 문의해 주세요.
