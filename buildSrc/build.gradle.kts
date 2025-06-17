plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    // precompiled script plugin 에서 플러그인 id()를 사용하려면
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.5.0")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
}