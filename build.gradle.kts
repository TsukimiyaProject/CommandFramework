plugins {
    kotlin("jvm") version "2.2.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    `maven-publish`
}

group = "mc.tsukimiya"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(23)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tsukimiyaproject/CommandFramework")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            artifactId = "db-connector"
            from(components["java"])
        }
    }
}
