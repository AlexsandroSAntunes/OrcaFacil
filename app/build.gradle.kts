plugins {
    id 'com.android.application'
}

android {
    namespace "com.orcafacil"
    compileSdk 36

    defaultConfig {
        applicationId "com.orcafacil"
        minSdk 24
        targetSdk 36
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation 'com.google.android.material:material:1.12.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

    // 📊 MPAndroidChart
    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
}
