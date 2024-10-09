#!/bin/sh

OS="$(uname | tr '[:upper:]' '[:lower:]')"


set -a
source "$(dirname "$0")/.env"
set +a

OUTPUT_DIR=${OUTPUT_DIR:-bin/}
MAIN_FILE=${MAIN_FILE:-src/ui/Main.java}
SRC=${SRC:-src/}

case "$OS" in
    linux*|darwin*)
        ;;
    msys*|cygwin*)
        OUTPUT_DIR=$(echo "$OUTPUT_DIR" | sed 's|/|\\|g')
        MAIN_FILE=$(echo "$MAIN_FILE" | sed 's|/|\\|g')
        SRC=$(echo "$SRC" | sed 's|/|\\|g')
        ;;
    *)
        echo "bruh"
        exit 1
        ;;
esac

if [ ! -d "$OUTPUT_DIR" ]; then
    mkdir $OUTPUT_DIR
fi

echo "Compiling..."
if [ "$OS" = "cygwin" ] || [ "$OS" = "msys" ]; then
    javac -d "$OUTPUT_DIR" "$SRC"\\*\\*.java
else
    javac -d "$OUTPUT_DIR" "$SRC"/*/*.java
fi

echo "Running main..."
java -cp "$OUTPUT_DIR" "$MAIN_FILE"
