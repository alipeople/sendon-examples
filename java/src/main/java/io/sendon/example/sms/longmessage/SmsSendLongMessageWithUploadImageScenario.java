package io.sendon.example.sms.longmessage;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.sendon.ApiException;
import io.sendon.example.BaseScenario;
import io.sendon.model.SendMessageRequestDto;
import io.sendon.model.SendMessageRequestDtoToInner;
import io.sendon.model.SendMessageResponseDto;
import io.sendon.model.SmsMessageType;
import io.sendon.model.UploadImage;
import io.sendon.model.UploadImagesResponseDto;

public class SmsSendLongMessageWithUploadImageScenario extends BaseScenario {

  @Override
  public void execute() {
    try {
      List<File> files = Arrays.asList(new File("./img/aligo.png"));
      UploadImagesResponseDto uploadResponse = sendon.getSms().uploadImages(files);
      System.out.println("응답:\n\t" + uploadResponse.toJson());
      List<UploadImage> images = uploadResponse.getData().getImages();
      List<String> imageIds = Arrays.asList(images.get(0).getId());
      
      SendMessageRequestDto request = new SendMessageRequestDto();
      request.setType(SmsMessageType.MMS);
      request.setFrom(SMS_MOBILE_FROM);
      request.setTo(Collections.singletonList(new SendMessageRequestDtoToInner(SMS_MOBILE_TO)));
      request.title(getDescription());
      request.images(imageIds);
      request.setMessage("안녕하세요. Sendon SDK Java를 이용한 테스트 메시지 입니다.(" + generateRandomCode() + ")");
      
      System.out.println("요청:\n\t" + request.toJson());
      SendMessageResponseDto response = sendon.getSms().send(request);
      System.out.println("응답:\n\t" + response.toJson());

    } catch (ApiException e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[MMS] 이미지 업로드 및 발송";
  }
}