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

@SpringBootTest
public class runCompileTest {
  @InjectMocks private CompileAPI compileAPI;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void runCompiledFile() throws ClassNotFoundException, MalformedURLException {

    try {
      URLClassLoader classLoader =
      URLClassLoader.newInstance(new URL[] {new File(".").toURI().toURL()});
      Class<?> cls = Class.forName("webCompile", true, classLoader);

      System.out.println("클래스 로드 성공");

      // 1. 클래스 로드

      // 2. 인스턴스 생성
      Method mainMethod = cls.getDeclaredMethod("main", String[].class);

      // 3. 메소드 접근 (인자가 없는 메소드를 가정)


      // 4. 메소드 실행
      Object result = mainMethod.invoke(null, (Object) new String[0]);



      //System.out.println(result.toString());
    } catch (IllegalAccessException
             | NoSuchMethodException
             | InvocationTargetException e) {
      e.printStackTrace();
      System.out.println("실행실패");
    }

    //return null;
  }
}
