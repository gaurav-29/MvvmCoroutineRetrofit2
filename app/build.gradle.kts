plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.mvvmcoroutineretrofit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmcoroutineretrofit"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Okhttp & logging-interceptor
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Lifecycle components - View Model, Live Data-  Not needed. Without this ViewModel & LiveData working fine.
//    implementation (libs.androidx.lifecycle.viewmodel.ktx)
//    implementation (libs.lifecycle.livedata.ktx)

    // Room Database
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
}