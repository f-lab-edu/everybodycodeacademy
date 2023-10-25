package com.every.everycodeacademy.API;

import com.every.everycodeacademy.compile.JavaCompile;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompileAPITest {

  @InjectMocks private CompileAPI compileAPI;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getJavaStringToCompileTest() {
    // 테스트할 Java 코드
    JavaCompile javaCompile = new JavaCompile();

    javaCompile.setJavaBodyString("hihi1231231231");
    javaCompile.setParameterString("args");
    javaCompile.setJavaFullCompile(
        "public class webCompile { public static String main(String[] "
            + javaCompile.getParameterString()
            + ") { return "
            + " \""
            + javaCompile.getJavaBodyString()
            + "\""
            + "; }}");

    // 테스트 실행
    compileAPI.getJavaStringToCompile(javaCompile);

    // 결과 검증 (여기서는 예시로 파일 존재 여부만 확인합니다)
    // 실제 상황에 따라 더 복잡한 검증 로직을 추가할 수 있습니다.
    File file = new File("webCompile.java");
    assertTrue(file.exists());

    // 컴파일된 파일이 생성되었는지 검사
    File compiledFile = new File("webCompile.class");
    assertTrue(compiledFile.exists());
  }
}
