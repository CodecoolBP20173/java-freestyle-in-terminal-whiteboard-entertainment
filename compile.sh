#!/bin/bash
mkdir -p target/build/libs
javac -d target -cp "libs/termlib.jar:src" src/com/codecool/tetris/Main.java
yes |cp -rf libs/*.jar target/build/libs/
cd target
touch MANIFEST.MF
printf "Manifest-Version: 1.0\nCreated-By: 1.8.0_161 (Oracle Corporation)\nMain-Class: com.codecool.tetris.Main\nClass-Path: libs/termlib.jar\n" > MANIFEST.MF
jar cfm build/tetris.jar MANIFEST.MF com/codecool/tetris/*.class