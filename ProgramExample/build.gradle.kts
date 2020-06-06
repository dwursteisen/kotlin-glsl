plugins {
    kotlin("jvm")
    kotlin("kapt")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(rootProject)
    kapt(rootProject)
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
