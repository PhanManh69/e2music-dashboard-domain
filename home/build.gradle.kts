plugins {
    id("feature-android-module")
}

android {
    namespace = "com.mobile.e2m.home"
}

dependencies {
    implementation(project(mapOf("path" to ":daily:menu")))
}
