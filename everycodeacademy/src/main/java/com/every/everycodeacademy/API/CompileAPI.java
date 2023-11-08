package com.every.everycodeacademy.API;

import com.every.everycodeacademy.compile.JavaCompile;
import com.every.everycodeacademy.compile.JavaCompileService;
import com.every.everycodeacademy.constant.Constants;
import java.io.BufferedWriter;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/complie")
public class CompileAPI {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired JavaCompileService javaCompileService;

  @RequestMapping("/java")
  public void getJavaStringToCompile(@RequestBody JavaCompile javaCompile) {
    JavaCompiler webCompiler = ToolProvider.getSystemJavaCompiler();


    String fileName = Constants.WEB_COMPILE_FILE;

    javaCompile.setJavaFullCompile(
        "public class webCompile { public static String main(String[] "
            + javaCompile.getParameterString()
            + ") { return "
            + " \""
            + javaCompile.getJavaBodyString()
            + "\""
            + "; }}");


    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".java"))) {
      writer.write(javaCompile.getJavaFullCompile());
      logger.info("소스 코드가   {}.java에 저장되었습니다.",fileName);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }

    // 3. 컴파일 옵션 설정
    Iterable<String> options = Arrays.asList("-d", "compiled");


    InputStream inputStream =
        new ByteArrayInputStream(javaCompile.getJavaFullCompile().getBytes());

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
      logger.error(e.getMessage());
    }

    try {

      //javaCompileService.runCompiledFile(fileName); // 컴파일된 파일 실행
      logger.info(javaCompileService.runCompiledFile(fileName));
    } catch (Exception e) {
      logger.error("logger error = {}", "컴파일 실행 실패");
      logger.error("logger error = {}", e.getMessage());
      throw new RuntimeException(e);
    }

    try {
      javaCompileService.changeFilePermissions(fileName+".java");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    try {
      javaCompileService.changeFilePermissions(fileName+".class");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    //

    // 실행에 필요했던 파일들 삭제
    try {
      javaCompileService.deleteFile(fileName+".java");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    try {
      javaCompileService.deleteFile(fileName+".class");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }
}
