plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)


    defaultConfig {
        applicationId = "com.prokarma.tmobile"
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    implementation("androidx.room:room-runtime:2.2.5")
    annotationProcessor("androidx.room:room-compiler:2.2.5")
    kapt(platform(project(":depconstraints")))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}")
    implementation(Libs.CORE_KTX)

    //UI
    implementation(Libs.ACTIVITY_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.MATERIAL)
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // Local unit tests
    testImplementation(Libs.JUNIT)

    // Instrumentation tests
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-beta01")

    implementation(Libs.GSON)

    //Retrofit
    implementation(Libs.RETROFIT_RUNTIME)
    implementation(Libs.RETROFIT_MOCK)
    implementation(Libs.RETROFIT_GSON)

    // OkHttp
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_VIEWMODEL)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kapt(Libs.ANDROIDX_HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.ANDROIDX_HILT_COMPILER)

    // Architecture Components
    implementation(Libs.LIFECYCLE_LIVE_DATA_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_RUNTIME)
    kapt(Libs.ROOM_COMPILER)
    testImplementation(Libs.ROOM_KTX)
    testImplementation(Libs.ROOM_RUNTIME)

    //Utils
    api(Libs.TIMBER)

    //Glide
    implementation(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)
}