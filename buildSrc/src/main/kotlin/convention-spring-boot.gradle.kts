plugins {
    `java-library`
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testRuntimeOnly("com.h2database:h2")
    // 테스트를 위해 추가
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        // JUnit Vintage 제외 (JUnit5만 사용한다면)
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}