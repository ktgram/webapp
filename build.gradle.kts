val jvmTargetVersion = JavaVersion.VERSION_11

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
}

group = "com.example.webapp"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = jvmTargetVersion.majorVersion
        }
    }
    js {
        browser()
        binaries.executable()
    }
    jvmToolchain(jvmTargetVersion.majorVersion.toInt())

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(libs.tg.bot)
            }
        }

        named("jsMain") {
            dependencies {
                implementation(libs.tg.webapp)
                implementation(libs.kotlinx.html)
            }
        }
    }
}

dependencies {
    add("kspJvm", libs.tg.ksp)
}