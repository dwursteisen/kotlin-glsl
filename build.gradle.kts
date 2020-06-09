import java.util.*

plugins {
    kotlin("jvm") version "1.3.70"
    id("com.jfrog.bintray") version "1.8.5"
    `maven-publish`
}

group = "com.salakheev"
version = "1.1"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.x5dev:chunk-templates:3.3.1")
    implementation("org.reflections:reflections:0.9.12")
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

val properties = Properties()
if (project.file("local.properties").exists()) {
    properties.load(project.file("local.properties").inputStream())
}


configure<com.jfrog.bintray.gradle.BintrayExtension> {
    user = properties.getProperty("bintray.user")
    key = properties.getProperty("bintray.key")
    publish = true
    setPublications("maven")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "minigdx"
        name = project.name
        githubRepo = "dwursteisen/kotlin-glsl.git"
        vcsUrl = "https://github.com/dwursteisen/kotlin-glsl.git"
        description = project.description
        setLabels("java")
        setLicenses("MIT")
        desc = description
        version(closureOf<com.jfrog.bintray.gradle.BintrayExtension.VersionConfig> {
            this.name = project.version.toString()
            released = Date().toString()
        })
    })
}
