
//1. Add below line
//===============================
buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.dzertak.customtoast"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro") //3. Add this line
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // << --- ADD This
    }
}
//===============================

java {
    sourceCompatibility = JavaVersion.VERSION_17            // << --- ADD This
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

// 5. Add below line
//===============================
//publishing {
//    publications {
//        maven(MavenPublication) {
//            groupId = 'com.github.geek-atif'
//            artifactId = 'com-atifqamar-customtoast'
//            version = "1.0"
//            pom {
//                description = 'First release'
//            }
//        }
//    }
//    repositories {
//        mavenLocal()
//    }
//}

publishing {
    publications {
        register<MavenPublication>("release",) {
            groupId = "com.github.dzertak"
            artifactId = "AndroidLib"
            version = "1.0.2"
            pom {
                description.set("First release")
            }
//            afterEvaluate {
//                //artifact(tasks.getByName("bundleRelease"))
//                //from(components["release"])
//            }
        }
        repositories {
            maven {
                mavenLocal()
//                name = "myrepoBase64"
//                url = uri("${project.buildDir}/repo")
            }
        }
    }
}