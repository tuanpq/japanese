import org.aspectj.tools.ajc.Main

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "io.github.tuanpq"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.github.tuanpq"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.aspectjrt)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.withType<JavaCompile>().configureEach {
    doLast {
        val args = arrayOf(
            "-showWeaveInfo",
            "-1.9",
            "-inpath", destinationDirectory.toString(),
            "-aspectpath", classpath.asPath,
            "-d", destinationDirectory.toString(),
            "-classpath", classpath.asPath,
            "-bootclasspath", android.bootClasspath.joinToString(File.pathSeparator)
        )

        Main().run(args, object : org.aspectj.bridge.MessageHandler(true) {
            override fun handleMessage(message: org.aspectj.bridge.IMessage): Boolean {
                when (message.kind) {
                    org.aspectj.bridge.IMessage.ERROR,
                    org.aspectj.bridge.IMessage.ABORT -> {
                        println("AspectJ ERROR: ${message.message}")
                    }
                    else -> {
                        println("AspectJ: ${message.message}")
                    }
                }
                return true
            }
        })
    }
}