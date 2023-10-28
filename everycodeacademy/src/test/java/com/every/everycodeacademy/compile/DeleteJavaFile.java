package com.every.everycodeacademy.compile;

import com.every.everycodeacademy.API.CompileAPI;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
public class DeleteJavaFile {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
  try{
      if(file.delete()){
        logger.debug(" info log = {}", "생성된 자바 파일이 성공적으로 삭제되었습니다.");
      }else{
        throw new Exception("생성된 자바 파일이 삭제되지 않았습니다.");
      }
    }catch(Exception e){
      logger.error("예외 발생: {}",e.getMessage(), e);
    }

  }
}
