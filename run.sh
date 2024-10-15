#!/bin/sh

set -a
source "$(dirname "$0")/.env"
set +a

OUTPUT_DIR=${OUTPUT_DIR:-bin/}
MAIN_FILE=${MAIN_FILE:-src/ui/Main.java}
SRC=${SRC:-src/}

if [ ! -d "$OUTPUT_DIR" ]; then
    mkdir $OUTPUT_DIR
fi

rm -rf bin

echo "Compiling..."
javac -d "$OUTPUT_DIR" "$SRC"/*/*.java -Xlint

echo "Running main..."
java -cp "$OUTPUT_DIR" "$MAIN_FILE"
