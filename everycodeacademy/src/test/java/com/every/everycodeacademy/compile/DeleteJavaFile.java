package com.every.everycodeacademy.compile;

import com.every.everycodeacademy.API.CompileAPI;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteJavaFile {

  @InjectMocks private CompileAPI compileAPI;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deleteJavaFile() {
    // 삭제하려는 파일의 경로
    String filePath = "webCompile.java";

    File file = new File(filePath);

    if (file.delete()) {
      System.out.println("생성된 자바 파일이 성공적으로 삭제되었습니다.");
      //return true;
    } else {
      System.out.println("생성된 자바 파일이 삭제 실패");
      //return false;
    }
  }
}
