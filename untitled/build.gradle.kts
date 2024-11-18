plugins {
    application
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "3.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
application {
    mainClass.set("org.example.MainKt")
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:3.0.1")
    implementation("io.ktor:ktor-server-netty:3.0.1")
    implementation("io.ktor:ktor-server-thymeleaf:3.0.1")
    implementation("org.ktorm:ktorm-core:3.2.0")
    implementation("org.postgresql:postgresql:42.1.4")
    implementation("org.ktorm:ktorm-support-postgresql:4.1.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}