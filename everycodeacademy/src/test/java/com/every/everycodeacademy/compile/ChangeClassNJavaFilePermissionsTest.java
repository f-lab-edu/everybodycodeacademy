package com.every.everycodeacademy.compile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChangeClassNJavaFilePermissionsTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  void changeClassNJavaFilePermissionsTest() {
    try {
      String filePathClass = "webCompile.class";
      String filePathJava = "webCompile.java";
      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(filePathClass), perms);
      logger.debug("Class 파일 권한 변경 성공");
      Files.setPosixFilePermissions(Paths.get(filePathJava), perms);
      logger.debug("Java 파일 권한 변경 성공");

      //return true;
    } catch (IOException e) {
      logger.error(e.getMessage());
      logger.error("파일 권한 변경 실패");

      //return false;
    }
  }
}
