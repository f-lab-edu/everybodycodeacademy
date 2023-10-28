package com.every.everycodeacademy.compile;

import com.every.everycodeacademy.API.CompileAPI;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootTest
public class runCompileTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @InjectMocks private CompileAPI compileAPI;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void runCompiledFile() throws ClassNotFoundException, MalformedURLException {

    try {
      URLClassLoader classLoader =
      URLClassLoader.newInstance(new URL[] {new File(".").toURI().toURL()}); //파일 로드
      Class<?> cls = Class.forName("webCompile", true, classLoader); //클래스 로드 class name : webCompile

      logger.info(" info log = {}", "클래스 로드 성공");

      // 2. 클래스의 main 메소드 로드
      Method mainMethod = cls.getDeclaredMethod("main", String[].class);

      // 3. 메소드 접근 (인자가 없는 메소드를 가정)


      // 4. 메소드 실행
      Object result = mainMethod.invoke(null, (Object) new String[0]);


      logger.info(" info log = {}", result.toString());
      //System.out.println(result.toString());
    } catch (IllegalAccessException
             | NoSuchMethodException
             | InvocationTargetException e) {
      e.printStackTrace();
      logger.error(" error log = {}", "컴파일 파일 실행 실패");
    }

    //return null;
  }
}
