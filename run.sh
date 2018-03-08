#!/bin/bash
stty raw -echo
java -jar target/build/tetris.jar
stty cooked echo
