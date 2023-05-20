plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.spring") version "1.8.0"
    application
}

group = "ua.its.kpi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation("org.springframework:spring-webmvc:6.0.6")
    // Thymeleaf
    implementation("org.thymeleaf:thymeleaf-spring6:3.1.1.RELEASE")
    // Jetty & Servlet API
    implementation("org.eclipse.jetty:jetty-webapp:11.0.14")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")

    // Logging
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")

    // Spring Data
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
    implementation("org.springframework.data:spring-data-jpa:3.0.3")

    // Hibernate
    implementation("org.hibernate:hibernate-core:6.1.7.Final")

    // In Memory DB
    implementation("org.hsqldb:hsqldb:2.7.1")


    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}

application {
    mainClass.set("ua.kpi.its.lab.rest.MainKt")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
    invokeInitializers = true
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}