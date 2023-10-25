package com.every.everycodeacademy.compile;

import com.every.everycodeacademy.API.CompileAPI;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteClassFile {

  @InjectMocks private CompileAPI compileAPI;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deleteClassFile() {
    String filePath = "webCompile.class";

    File file = new File(filePath);

    if (file.delete()) {
      System.out.println("생성된 클래스 파일이 성공적으로 삭제되었습니다.");
      //return true;
    } else {
      System.out.println("생성된 클래스 파일 삭제 실패");
      //return false;
    }
  }
}
