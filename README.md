# Protect the Pointer

A Java Swing game where you protect your mouse pointer from incoming obstacles.

## Download & Play (Mac)

1. Go to the [Releases](https://github.com/audicakes/ProtectThePointer/releases) page
2. Download `ProtectThePointer-1.0.dmg`
3. Open the `.dmg`, drag **ProtectThePointer** into your Applications folder
4. Right-click the app → **Open** → **Open Anyway** (required the first time since the app is unsigned)
5. Double-click to play — no Java installation needed

## Build from Source

Requires Java 14+.

```
bash build.sh
```

This compiles the source, packages everything into a JAR, and produces a `.dmg` installer in the `dist/` folder.

## Project Structure

```
PTP_Main.java       # main game file
obstacles/          # obstacle classes (bullets, lasers, etc.)
powerups/           # powerup classes (coins, heals, etc.)
audio/              # music and sound effects
images/             # game images and icons
archives/           # old prototype files
build.sh            # build script
```

## Author

Kevin Zhang — AP CS A final project
