plugins {
	java
	id("maven-publish")
	id("org.springframework.boot") version "4.0.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.imarkov"
version = "0.0.1-SNAPSHOT"
description = "Logging system in mircoservice app"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-kafka")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
	implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")

	testImplementation("org.springframework.boot:spring-boot-starter-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			groupId = "com.example"        // your group
			artifactId = "logging-kafka-lib" // library name
			version = "1.0.0"

			from(components["java"])
		}
	}

	repositories {
		mavenLocal()   // publish to ~/.m2/repository
	}
}