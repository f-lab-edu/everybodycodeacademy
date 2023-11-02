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
public class ChangeClassFilePermissions {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Test
  void changeClassFilePermissions() {
    try {
      String filePath = "webCompile.class";
      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(filePath), perms);
      logger.debug(" info log = {}", "Class 파일 권한 변경 성공");
    } catch (IOException e) {
      e.printStackTrace();
      logger.debug(" info log = {}", "Class 파일 권한 변경 실패");
    }
  }
}
