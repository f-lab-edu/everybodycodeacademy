package com.every.everycodeacademy.API;

import static org.junit.jupiter.api.Assertions.*;

import com.every.everycodeacademy.compile.JavaCompile;
import com.every.everycodeacademy.compile.JavaCompileService;
import com.every.everycodeacademy.constant.Constants;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompileAPITest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @InjectMocks private JavaCompile javaCompile;

  @InjectMocks JavaCompileService javaCompileService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getJavaStringToCompileTest() {
    JavaCompiler webCompiler = ToolProvider.getSystemJavaCompiler();

    String fileName = Constants.WEB_COMPILE_FILE;
    javaCompile.setParameterString("args");

    javaCompile.setJavaBodyString("good after noon");
    assertNotNull(javaCompile.getJavaBodyString());

    javaCompile.setJavaFullCompile(
        "public class webCompile { public static String main(String[] "
            + javaCompile.getParameterString()
            + ") { return "
            + " \""
            + javaCompile.getJavaBodyString()
            + "\""
            + "; }}");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".java"))) {
      writer.write(javaCompile.getJavaFullCompile());
      System.out.println("소스 코드가 " + fileName + ".java에 저장되었습니다.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 3. 컴파일 옵션 설정
    Iterable<String> options = Arrays.asList("-d", "compiled");

    // 4. 컴파일 수행
    try (StandardJavaFileManager fileManager =
        webCompiler.getStandardFileManager(null, null, null)) {


      // 컴파일할 파일 지정
      String[] compileArguments = {fileName + ".java"};

      // 컴파일 실행
      int compilationResult = webCompiler.run(null, null, null, compileArguments);

      if (compilationResult == 0) {
        logger.info("logger info = {}", "컴파일 성공");
      } else {
        logger.error("logger error = {}", "컴파일 중 오류가 발생하였습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      assertNotNull(javaCompileService.runCompiledFile(fileName)); // 컴파일된 파일 실행
      logger.info(javaCompileService.runCompiledFile(fileName));
    } catch (Exception e) {
      logger.error("logger error = {}", "컴파일 실행 실패");
      logger.error("logger error = {}", e.getMessage());
      throw new RuntimeException(e);
    }

    try {
      assertTrue (javaCompileService.changeFilePermissions(fileName, ".java"));
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }

    try {
      assertTrue (javaCompileService.changeFilePermissions (fileName, ".class"));
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }

    // 실행에 필요했던 파일들 삭제
    assertTrue (javaCompileService.deleteFile(fileName, ".java"));
    assertTrue(javaCompileService.deleteFile(fileName, ".class"));
  }
}
