#!/bin/bash

# Script to compile and run the Kotlin Adventure Game
# AnanyaTalentWorkshop

echo "🎮 Starting Ananya Talent Workshop Adventure Game..."
echo "=================================================="

# Check if Kotlin compiler is available
if ! command -v kotlinc &> /dev/null; then
    echo "❌ Error: Kotlin compiler (kotlinc) not found!"
    echo "Please install Kotlin from: https://kotlinlang.org/docs/command-line.html"
    echo "Or install via package manager:"
    echo "  - macOS: brew install kotlin"
    echo "  - Ubuntu/Debian: sudo apt install kotlin"
    exit 1
fi

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "❌ Error: Java not found!"
    echo "Please install Java 8 or higher"
    exit 1
fi

# Create output directory if it doesn't exist
mkdir -p out/production

echo "🔨 Compiling Kotlin sources..."

# Compile all Kotlin files with Kotlin runtime included
kotlinc src/*.kt src/com/github/mm/coloredconsole/*.kt -include-runtime -d out/production/AnanyaTalentWorkshop.jar

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo "🚀 Running the game..."
    echo "=================================================="
    echo ""

    # Run the JAR file in a restart loop
    echo "💡 Press Ctrl+C to restart the game, Ctrl+\\ to exit completely"
    echo ""

    while true; do
        java -jar out/production/AnanyaTalentWorkshop.jar
        echo ""
        echo "🔄 Game ended. Restarting in 2 seconds..."
        echo "   Press Ctrl+\\ to exit, or Ctrl+C to restart immediately"
        sleep 2 || break
    done
else
    echo "❌ Compilation failed!"
    echo "Please check the error messages above and fix any issues."
    exit 1
fi
