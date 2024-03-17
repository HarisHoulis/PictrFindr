# PicFind App

## Build  :warning:

:rotating_light: Before building the app, make sure to include your API_KEY at your local `local.properties` file.

## Architecture

The app uses a straightforward MVI approach that targets _small_ to _mid_ sized apps, **however** it can be scaled to for larger apps
when adding additional classes to split responsibilities (e.g. `Actor`, `Reducer`).

At it's core:
* It uses `AndroidX ViewModel`, for persisting state across configuration changes
* It uses Kotlin's `MutableStateFlow` (exposed as `StateFlow`) to store the UI's state.
* Exposes `fun`ctions that represent _User Events_
* Interacts with a Repository interface (injected at runtime), in an **asynchronous** way, to cover the client's needs.

### `PicturesViewModel`

Here we also leverage the handy powerful `Flow` APIs (`debounce`, `collectLatest`) to minimize our calls to the back-end,
as well handle gracefully changes of the user's query.

Additionally, [Result4k](https://github.com/fork-handles/forkhandles/tree/trunk/result4k) for type safety and idiomatic handling
of the responses from the "outer-world".

## Modularization

### Build logic

First and foremost, we leverage the **Version Catalog** feature of Gradle together with the `libs.versions.toml` file.
However, where our `build-logic` shines is by making use of [Composite Builds](https://docs.gradle.org/current/userguide/composite_builds.html)
and [Idiomatic Gradle](https://github.com/jjohannes/idiomatic-gradle).

This allows us to have a single source of truth regarding our dependencies, as well quickly configure new modules 
(from kotlin/java libs to apps). See [build-logic/convention](build-logic/convention) & [organising build logic](https://docs.gradle.org/current/samples/sample_convention_plugins.html#organizing_build_logic)

### Modules

Our app consists of:
* **1** `:app` module, that interwines everything together
* **2** `:feature` odules, that define the UI logic of our business
* **4** :core modules, that handle
    * sharing UI resources (`:core:design`)
    * sharing our domain models as well defining our API (`:core:domain`)
    * communication with the "outer world", mapping raw to domain models (`:core:network`)
    * providing handy libs and fake data for testing (`:core:test`)

### Testing

Our **business** logic is covered with Unit tests, backed by `JUnit5` and `Kotest`.

Our **presentation** logic is covered with:
1. Integration tests for our `ViewModel`s: here we leverage `Turbine` and our custom `runTestWithDispatcher` `fun`.
2. UI tests for our `Composable`s.

## Libraries

* Compose
* Datastore
* Hilt
* Compose Navigation
* Coil
* Result4k
* Kotlin Coroutines & Flows
* Kotlinx Serialization
* Kotest
* JUnit4 & JUnit5
* Okhttp Logging
* Retrofit
* Turbine
* Spotless + tlint
