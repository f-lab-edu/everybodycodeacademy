package com.every.everycodeacademy.compile;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class JavaCompile {
    LocalDateTime now = LocalDateTime.now();


    private LocalDateTime localDateTime = now;
    private String javaBodyString;
    private String username;
    private String parameterString;
    private String javaFullCompile;
}
