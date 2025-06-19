plugins {
    `java-library`
    id("convention-java")
    id("io.spring.dependency-management")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    api("org.springframework:spring-context")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.0")
    }
}
