#!/bin/bash
set -e
cd "$(dirname "$0")"

echo "==> Compiling..."
mkdir -p out
javac -d out obstacles/*.java powerups/*.java PTP_Main.java

echo "==> Copying resources..."
cp -r audio out/
cp -r images out/

echo "==> Creating JAR..."
echo "Main-Class: PTP_Main" > manifest.txt
jar cfm ProtectThePointer.jar manifest.txt -C out .
rm manifest.txt

echo "==> Packaging into .app..."
rm -rf dist
jpackage \
  --input . \
  --main-jar ProtectThePointer.jar \
  --main-class PTP_Main \
  --name "ProtectThePointer" \
  --mac-package-identifier com.kevinzhang.protectthepointer \
  --type dmg \
  --dest dist

echo ""
echo "Done! Find the installer at: dist/ProtectThePointer-1.0.dmg"
echo "Open it, drag the app to Applications, then double-click to play."
