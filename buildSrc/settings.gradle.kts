pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        // 여기는 buildSrc 전용입니다
        id("org.springframework.boot") version "3.5.0"
        id("io.spring.dependency-management") version "1.1.7"
    }
}

rootProject.name = "buildSrc"