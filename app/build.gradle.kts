plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.example.androidrepo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidrepo.application.GitHubRepositoriesApplication"
        minSdk = 24
        targetSdk = 35
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.activity)

    // Retrofit
    implementation(libs.refrotit)
    implementation(libs.refrotit.converter.gson)
    implementation(libs.refrotit.converter.scalars)

    // Okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Glide
    implementation(libs.glide)
    testImplementation(libs.androidx.test.runner)

    // Dagger
    kapt(libs.dagger.hilt.android.compiler)
    implementation(libs.dagger.hilt)

    // Unit Test

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testImplementation(libs.dagger.hilt.android.testing)
    kaptAndroidTest(libs.dagger.hilt.android.compiler)

    androidTestImplementation(libs.androidx.core.test)
    androidTestImplementation(libs.androidx.core.ktx.test)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}