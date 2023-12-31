package com.every.everycodeacademy.compile;

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

  public boolean deleteFile(String fileName, String extension) {
    String fileNameNClass = fileName + extension;
    File fileClass = new File(fileNameNClass);


    try {
      if (fileClass.delete()) {
        logger.debug(" info log = {}", "생성된 " + fileNameNClass + "파일이 성공적으로 삭제되었습니다.");
        return true;
        // logger.debug(" info log = {}", "생성된 Java 파일이 성공적으로 삭제되었습니다.");
      } else {
        throw new Exception("생성된 파일 삭제 실패");
      }
    } catch (Exception e) {
      logger.error("{} 파일 삭제 실패. 오류: {}", fileNameNClass, e.getMessage());

      return false;
    }
  }

  public boolean changeFilePermissions(String fileName, String extension) throws Exception {
    String fileNameNclass = fileName + extension;

    try {

      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(fileNameNclass), perms);
      logger.debug("{} 파일 권한 변경 성공", fileNameNclass);

      return true;
    } catch (IOException e) {
      logger.error("{} 파일 권한 변경 실패. 오류: {}", fileNameNclass, e.getMessage());

      return false;
    }
  }

  public String runCompiledFile(String fileName)
      throws ClassNotFoundException, MalformedURLException {
    Object result = new Object();
    try {
      URLClassLoader classLoader =
          URLClassLoader.newInstance(new URL[] {new File(".").toURI().toURL()});
      Class<?> cls = Class.forName(fileName, true, classLoader);

      // 2. 인스턴스 생성
      Method mainMethod = cls.getDeclaredMethod("main", String[].class);

      result = mainMethod.invoke(null, (Object) new String[0]);

    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      logger.debug("컴파일 실행 실패");
      return null;
    }
    logger.debug("컴파일 실행 성공");
    return result.toString();
  }
}
