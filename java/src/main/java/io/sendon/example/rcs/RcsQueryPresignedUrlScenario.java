package io.sendon.example.rcs;

import io.sendon.Log;
import io.sendon.example.BaseScenario;
import io.sendon.rcs.response.CreateCsvUploadUrl;

public class RcsQueryPresignedUrlScenario extends BaseScenario {

  @Override
  public void execute() {
    try {
      CreateCsvUploadUrl result = sendon.rcs.createCsvUploadUrl();
      Log.d("CreateCsvUploadUrl: " + gson.toJson(result));

      if (result.code == 200) {
        Log.d("업로드 URL: " + result.data.presignedUrl);
        Log.d("버킷: " + result.data.bucket);
        Log.d("파일 키: " + result.data.key);
      }
    } catch (Exception e) {
      handleException(e);
    }
  }

  @Override
  public String getDescription() {
    return "[RCS] CSV 업로드용 Presigned URL 조회";
  }
}