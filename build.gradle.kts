plugins {
    kotlin("jvm") version "1.3.70"
    `maven-publish`
}

group = "com.salakheev"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.x5dev:chunk-templates:3.3.1")
    implementation(kotlin("stdlib"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
