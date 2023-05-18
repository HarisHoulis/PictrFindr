plugins {
    id("compose-app")
}

dependencies {
    implementation(project(":feature:list"))
    implementation(project(":feature:details"))
}

afterEvaluate {
    tasks
        .filter { task ->
            task.name.equals("clean", ignoreCase = true) ||
                    task.name.contains("assemble", ignoreCase = true)
        }.forEach { task ->
            task.dependsOn(":installGitHooks")
        }
}
