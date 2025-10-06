// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        // Repositórios onde o Gradle procura as dependências
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        // Classpath para os plugins do Gradle
        classpath("com.android.tools.build:gradle:8.5.0")

        // ADICIONE ESTA LINHA: Classpath para o plugin do Google Services
        classpath("com.google.gms:google-services:4.4.2")
    }
}

// O bloco 'plugins' pode ser mantido ou removido se as declarações já estão no buildscript
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}
