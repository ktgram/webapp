plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.telegram.bot)
    application
    distribution
}

group = "com.example.webapp"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm { withJava() }
    js {
        browser()
        binaries.executable()
    }
    jvmToolchain(17)

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.html)
                implementation(libs.tg.utils)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.tg.ktor)
            }
        }
        jsMain {
            dependencies {
                implementation(libs.tg.webapp)
            }
        }
    }
}

tasks {
    named("jvmProcessResources") { mustRunAfter("copyJsResources") }
    build { dependsOn("copyJsResources") }
    create<Copy>("copyJsResources") {
        mustRunAfter("jsBrowserDistribution")
        from(layout.buildDirectory.dir("dist/js/productionExecutable"))
        into(layout.projectDirectory.dir("src/jvmMain/resources/static"))
    }
}

application {
    mainClass.set("com.example.webapp.TgBotApplicationKt")
}