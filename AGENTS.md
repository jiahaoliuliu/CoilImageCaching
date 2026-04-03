# AGENTS.md - Coding Guidelines for CoilImageCaching

This document provides guidelines for AI agents working on the CoilImageCaching codebase.

## Project Overview

**CoilImageCaching** is an Android Jetpack Compose application demonstrating image caching with the Coil library. The project uses Kotlin, Gradle, and follows Material Design 3 patterns.

- **Language**: Kotlin
- **Build System**: Gradle (Kotlin DSL)
- **Min SDK**: 24 | **Target SDK**: 36
- **Compose**: Jetpack Compose for UI
- **Key Library**: Coil 3 for image loading and caching

---

## Build/Lint/Test Commands

### Build Commands
```bash
# Full build (assembles and tests)
./gradlew build

# Build debug APK only
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean build
```

### Test Commands
```bash
# Run all unit tests for all variants
./gradlew test

# Run unit tests for debug variant only
./gradlew testDebugUnitTest

# Run instrumentation tests (requires connected device/emulator)
./gradlew connectedDebugAndroidTest

# Run all checks (tests + lint + other verifications)
./gradlew check
```

### Lint Commands
```bash
# Run lint checks
./gradlew lint

# Run lint with fix (applies safe suggestions automatically)
./gradlew lintFix

# Run lint for debug variant
./gradlew lintDebug
```

### Install & Run
```bash
# Install debug build on connected device
./gradlew installDebug

# Uninstall debug build
./gradlew uninstallDebug
```

---

## Code Style Guidelines

### Kotlin Code Style

**Configuration**: The project uses the **official Kotlin code style** (`kotlin.code.style=official` in gradle.properties).

#### Imports
- Use explicit imports, no wildcard imports (`import foo.bar.*` is avoided)
- Organize imports alphabetically within groups
- Group imports by source: Android → AndroidX → Third-party → Project packages
- Remove unused imports

#### Formatting
- **Indentation**: 4 spaces
- **Line Length**: 120 characters (Android default)
- **Braces**: Opening brace on same line (K&R style)
- **Naming**: camelCase for variables/functions, PascalCase for classes/interfaces

#### Naming Conventions
- **Classes/Interfaces**: PascalCase (e.g., `MainActivity`, `AsyncImage`)
- **Functions/Variables**: camelCase (e.g., `printOutMemory()`, `contentScale`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MEMORY_THRESHOLD`)
- **Private Members**: Prefix with underscore optional (e.g., `_state`)

#### Function/Composable Patterns
- Composable functions should be PascalCase if they compose UI (e.g., `ItemRow`)
- Private composables use `@Composable` annotation
- Default parameters and named arguments for readability
- Functions longer than 3 lines should have explicit return type

#### Types & Null Safety
- Use **non-null types by default** in Kotlin (`.` not `?`)
- Nullable types require explicit `?` annotation
- Use safe calls (`?.`) and Elvis operator (`?:`) appropriately
- Avoid `!!` (non-null assertion) except in test code
- Prefer `when` expressions over if-else for exhaustive checks

#### Error Handling
- Use **try-catch** for IO/network operations
- Prefer **Result<T>** or sealed classes for domain errors
- Log errors with meaningful context: `println()` for debugging (as seen in `printOutMemory()`)
- Never silently suppress exceptions without documentation

#### Comments
- Use `//` for single-line comments
- Use `/** */` for KDoc on public APIs
- Document **why**, not **what** (code is self-documenting)
- Remove commented-out code blocks (as seen in MainActivity.kt, but prefer deletion)

### Android-Specific Patterns

#### Jetpack Compose
- Use `Modifier` parameter as first parameter in Composable functions
- Default values for `Modifier = Modifier`
- Extract complex Composables into separate functions for reusability
- Use state hoisting for non-local state management
- Mutable state with `remember` for local UI state

Example from codebase:
```kotlin
@Composable
private fun ItemRow(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        // Content
    }
}
```

#### Activity Lifecycle
- Initialize UI in `onCreate()` using `setContent {}`
- Use `enableEdgeToEdge()` for edge-to-edge layouts
- Lifecycle-aware resource management

#### Resource References
- Use resource IDs from generated `R` class (e.g., `R.drawable.coillogo`)
- Use string resources for user-facing text
- Use dimension resources (dp) for spacing/sizing

### Project Structure

```
app/src/
├── main/
│   ├── java/com/jiahaoliuliu/coilimagecaching/
│   │   ├── MainActivity.kt
│   │   ├── MyApplication.kt
│   │   └── ui/theme/
│   └── res/
├── test/java/                    # Unit tests
└── androidTest/java/             # Instrumentation tests
```

---

## Common Development Tasks

### Running a Single Test
```bash
# Unit test
./gradlew testDebugUnitTest --tests com.jiahaoliuliu.coilimagecaching.ExampleUnitTest

# Instrumentation test (requires device)
./gradlew connectedDebugAndroidTest --tests com.jiahaoliuliu.coilimagecaching.ExampleInstrumentedTest
```

### Creating New Files
- **Activity**: Place in `java/com/jiahaoliuliu/coilimagecaching/`
- **Composables**: Can go in same package or `ui/` subpackage
- **Tests**: Mirror package structure in `test/` and `androidTest/`

### Dependencies
- Dependencies use **version catalogs** (`libs.*` notation)
- Add to `app/build.gradle.kts` dependencies block
- Key libraries: Coil 3, Compose, AndroidX

---

## Memory Optimization Strategy

The application implements several memory optimization techniques for image loading with Coil:

### Key Optimizations
1. **Reduced Memory Cache**: 5% of available memory (vs default 10%)
2. **Disabled Strong References**: Allow immediate garbage collection of bitmaps
3. **RGB_565 Bitmap Config**: 50% memory savings vs ARGB_8888 (no transparency)
4. **Size-Constrained Loading**: Load images at exact display dimensions (400×128px)
5. **Disk Cache Priority**: Leverage persistent disk storage over RAM

### Memory Savings
- **Per-image**: ~98% reduction when loading full-resolution images scaled to display size
- **Overall**: ~75-85% total memory footprint reduction
- **Example**: 5 images at 2400×2400 reduced from ~20-30 MB to ~2-5 MB in memory

**For detailed information**, see `MEMORY_OPTIMIZATION.md`

---

1. Run `./gradlew lintFix` to fix lint issues
2. Run `./gradlew testDebugUnitTest` to verify unit tests pass
3. Verify no unused imports
4. Check that new public APIs have KDoc comments
5. No commented-out code left behind

