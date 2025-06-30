@echo off
REM Script to compile and run the Kotlin Adventure Game
REM AnanyaTalentWorkshop

echo 🎮 Starting Ananya Talent Workshop Adventure Game...
echo ==================================================

REM Check if Kotlin compiler is available
kotlinc -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Error: Kotlin compiler ^(kotlinc^) not found!
    echo Please install Kotlin from: https://kotlinlang.org/docs/command-line.html
    echo Or install via package manager:
    echo   - Windows: choco install kotlin
    echo   - Or download from GitHub releases
    pause
    exit /b 1
)

REM Check if Java is available
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Error: Java not found!
    echo Please install Java 8 or higher
    pause
    exit /b 1
)

REM Create output directory if it doesn't exist
if not exist "out\production" mkdir out\production

echo 🔨 Compiling Kotlin sources...

REM Compile all Kotlin files with Kotlin runtime included
kotlinc src\*.kt src\com\github\mm\coloredconsole\*.kt -include-runtime -d out\production\AnanyaTalentWorkshop.jar

REM Check if compilation was successful
if %errorlevel% equ 0 (
    echo ✅ Compilation successful!
    echo 🚀 Running the game...
    echo ==================================================
    echo.

    REM Run the JAR file
    java -jar out\production\AnanyaTalentWorkshop.jar
) else (
    echo ❌ Compilation failed!
    echo Please check the error messages above and fix any issues.
    pause
    exit /b 1
)

pause
