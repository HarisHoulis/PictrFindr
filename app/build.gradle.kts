plugins {
    id("compose-app")
}

dependencies {
    implementation(project(":feature:list"))
    implementation(project(":feature:details"))
}
