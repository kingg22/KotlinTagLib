import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        extraWarnings.set(true)
        allWarningsAsErrors.set(true)
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    explicitApi()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("androidAndJvm") {
                withAndroidTarget()
                withJvm()
            }
        }
    }

    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    sourceSets {
        androidInstrumentedTest.dependencies {
            implementation(libs.androidx.runner)
            implementation(libs.androidx.rules)
        }
    }
}

android {
    namespace = "com.kyant.taglib"
    // https://developer.android.com/tools/releases/platforms
    compileSdk = 36
    buildToolsVersion = "36.0.0"
    ndkVersion = "29.0.13599879"

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
        ndk {
            abiFilters += arrayOf("arm64-v8a", "armeabi-v7a", "x86_64", "x86")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "4.0.4"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
