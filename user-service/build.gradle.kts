plugins {
    java
    id("convention-java")
    id("convention-spring-boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.5")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("com.mysql:mysql-connector-j")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

