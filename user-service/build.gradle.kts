plugins {
    java
    id("convention-java")
    id("convention-spring-boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.5")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

