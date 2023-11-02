package com.every.everycodeacademy.compile;

import com.every.everycodeacademy.constant.Constants;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JavaCompileService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public boolean deleteJavaFile() {

    File file = new File(Constants.WEB_COMPILE_JAVA_FILE_PATH);
    try {
      if (file.delete()) {
        logger.debug(" info log = {}", "생성된 자바 파일이 성공적으로 삭제되었습니다.");
        return true;
      } else {
        throw new Exception("생성된 자바 파일이 삭제되지 않았습니다.");
      }
    } catch (Exception e) {
      logger.error("예외 발생: {}", e.getMessage(), e);
      return false;
    }
  }

  public boolean deleteClassFile() {
    String filePath = "webCompile.class";

    File file = new File(Constants.WEB_COMPILE_CLASS_FILE_PATH);
    try {
      if (file.delete()) {
        logger.debug(" info log = {}", "생성된 클래스 파일이 성공적으로 삭제되었습니다.");
        return true;
      } else {
        throw new Exception("생성된 클래스 파일 삭제 실패");
      }
    } catch (Exception e) {
      logger.error(" info log = {}", "생성된 클래스 파일 삭제 실패");
      return false;
    }
  }

  public void deleteClassNJavaFile() {
    String filePathClass = "webCompile.class";
    String filePathJava = "webCompile.java";

    File fileClass = new File(Constants.WEB_COMPILE_CLASS_FILE_PATH);
    File fileJava = new File(Constants.WEB_COMPILE_JAVA_FILE_PATH);
    try {
      if (fileClass.delete() || fileJava.delete()) {
        logger.debug(" info log = {}", "생성된 Class 파일이 성공적으로 삭제되었습니다.");
        logger.debug(" info log = {}", "생성된 Java 파일이 성공적으로 삭제되었습니다.");
      } else {
        throw new Exception("생성된 파일 삭제 실패");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      logger.error(" info log = {}", "생성된 파일 삭제 실패");
    }
  }

  public boolean changeJavaFilePermissions() throws Exception {
    try {

      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(Constants.WEB_COMPILE_JAVA_FILE_PATH), perms);
      logger.debug("Java 파일 권한 변경 성공");

      return true;
    } catch (IOException e) {
      logger.error(e.getMessage());
      logger.error("Java 파일 권한 변경 실패");

      return false;
    }
  }

  public boolean changeClassFilePermissions() throws Exception {
    try {

      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(Constants.WEB_COMPILE_CLASS_FILE_PATH), perms);

      logger.debug("Class 파일 권한 변경 성공");

      return true;
    } catch (IOException e) {
      logger.error(e.getMessage());
      logger.error("Class 파일 권한 변경 실패");

      return false;
    }
  }

  public boolean changeClassNJavaFilePermissions() throws Exception {
    try {
      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(Constants.WEB_COMPILE_CLASS_FILE_PATH), perms);
      logger.debug("Class 파일 권한 변경 성공");
      Files.setPosixFilePermissions(Paths.get(Constants.WEB_COMPILE_JAVA_FILE_PATH), perms);
      logger.debug("Java 파일 권한 변경 성공");

      return true;
    } catch (IOException e) {
      logger.error(e.getMessage());
      logger.error("파일 권한 변경 실패");

      return false;
    }
  }

  public String runCompiledFile() throws ClassNotFoundException, MalformedURLException {
    Object result = new Object();
    try {
      URLClassLoader classLoader =
          URLClassLoader.newInstance(new URL[] {new File(".").toURI().toURL()});
      Class<?> cls = Class.forName(Constants.WEB_COMPILE_FILE, true, classLoader);

      // 2. 인스턴스 생성
      Method mainMethod = cls.getDeclaredMethod("main", String[].class);

      result = mainMethod.invoke(null, (Object) new String[0]);

    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      logger.debug("컴파일 실패");
      return null;
    }
    logger.debug("컴파일 성공");
    return result.toString();
  }
}
