package com.every.everycodeacademy.compile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;

import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChangeJavaFilePermissions {

  @Test
  void changeJavaFilePermissions() {
    try {
      String filePath = "webCompile.java";
      // 변경할 권한 설정
      Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");

      // 권한 변경
      Files.setPosixFilePermissions(Paths.get(filePath), perms);
      System.out.println("Java 파일 권한 변경 성공");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Java 파일 권한 변경 실패");
    }
  }
}
