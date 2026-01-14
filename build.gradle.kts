import org.gradle.kotlin.dsl.compileOnly

plugins {
	java
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "org.launchcode"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")


    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    runtimeOnly("com.mysql:mysql-connector-j")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
// Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

// Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-data")

// Thymeleaf extras pentru Security (opțional acum, dar util mai târziu)
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

// Test Security
    testImplementation("org.springframework.security:spring-security-test")
    implementation("org.springframework.security:spring-security-web")


}


tasks.withType<Test> {
	useJUnitPlatform()
}
