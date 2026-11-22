#!/usr/bin/env bash

set -u

ROOT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
cd "$ROOT_DIR" || exit 1

printf '%s\n' \
  ' ____        _ _     _    ____ _' \
  '| __ ) _   _(_) | __| |  / ___| |__   __ _ _ __   __ _  ___  __| |  / ___|_ __ ___  __ _| |_ _   _ _ __ ___  ___' \
  "|  _ \| | | | | |/ _  | | |   | |_ \ / _  | '_ \ / _  |/ _ \/ _  | | |   | '__/ _ \/ _  | __| | | | '__/ _ \/ __|" \
  '| |_) | |_| | | | (_| | | |___| | | | (_| | | | | (_| |  __/ (_| | | |___| | |  __/ (_| | |_| |_| | | |  __/\__ \' \
  '|____/ \__,_|_|_|\__,_|  \____|_| |_|\__,_|_| |_|\__, |\___|\__,_|  \____|_|  \___|\__,_|\__|\__,_|_|  \___||___/' \
  '                                                  |___/'

export GRADLE_OPTS="-Xmx2G"
res_dir="src/generated/resources"

echo "[1/3] Cleaning generated resources..."
if [ -d "$res_dir" ]; then
  rm -rf -- "$res_dir"
else
  echo "Directory not found, skipping cleanup: $res_dir"
fi

echo "[2/3] Running data generation..."
if ./gradlew runData; then
  :
else
  status=$?
  echo "Data generation failed with exit code $status" >&2
  exit "$status"
fi

echo "[3/3] Building..."
if ./gradlew build; then
  :
else
  status=$?
  echo "Build failed with exit code $status" >&2
  exit "$status"
fi

echo "Build completed successfully!"
