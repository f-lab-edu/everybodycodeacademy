package com.every.everycodeacademy.API;

import com.every.everycodeacademy.compile.JavaCompile;
import com.every.everycodeacademy.compile.JavaCompileService;
import java.io.BufferedWriter;
import java.net.MalformedURLException;
import java.util.Arrays;
import javax.tools.StandardJavaFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/complie")
public class CompileAPI {

  @Autowired JavaCompileService javaCompileService;

  @RequestMapping("/java")
  public void getJavaStringToCompile(@RequestBody JavaCompile javaCompile) {

    JavaCompiler webCompiler = ToolProvider.getSystemJavaCompiler();

    String filePath = "webCompile.java";

    // javaCompile.setJavaBodyString("hihi1231231231");
    // javaCompile.setParameterString("args");
    javaCompile.setJavaFullCompile(
        "public class webCompile { public static String main(String[] "
            + javaCompile.getParameterString()
            + ") { return "
            + " \""
            + javaCompile.getJavaBodyString()
            + "\""
            + "; }}");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(javaCompile.getJavaFullCompile());
      System.out.println("소스 코드가 " + filePath + "에 저장되었습니다.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 3. 컴파일 옵션 설정
    Iterable<String> options = Arrays.asList("-d", "compiled");

    // 4. 컴파일 수행
    try (StandardJavaFileManager fileManager =
        webCompiler.getStandardFileManager(null, null, null)) {
      String fileName = "webCompile.java";

      // 컴파일할 파일 지정
      String[] compileArguments = {fileName};

      // 컴파일 실행
      int compilationResult = webCompiler.run(null, null, null, compileArguments);

      if (compilationResult == 0) {
        System.out.println("컴파일이 성공적으로 완료되었습니다.");
      } else {
        System.out.println("컴파일 중 오류가 발생하였습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      System.out.println(javaCompileService.runCompiledFile()); // 컴파일된 파일 실행
    } catch (ClassNotFoundException | MalformedURLException e) {
      throw new RuntimeException(e);
    }

    // 삭제 위해 파일 권한 변경
    javaCompileService.changeClassFilePermissions();
    javaCompileService.changeClassFilePermissions();

    // 실행에 필요했던 파일들 삭제
    javaCompileService.deleteJavaFile();
    javaCompileService.deleteClassFile();
  }
}
