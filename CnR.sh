#!/bin/bash
javac -d output *.java
cd output/
java Tester
cd ..
rm -r output
