#!/bin/zsh
set -eu

dayArg=$1
if [[ dayArg -lt 10 ]]; then
  dayNumber="0${dayArg}";
else
  dayNumber=$dayArg;
fi

mainDir=src/main/kotlin/adventcode/day"${dayNumber}"
testDir=src/test/kotlin/adventcode/day"${dayNumber}"
mkdir $mainDir
mkdir $testDir

mainFile="$mainDir/day${dayNumber}.kt"
testFile="$testDir/Day${dayNumber}Test.kt"

echo "package adventcode.day${dayNumber}\n" > $mainFile
echo "package adventcode.day${dayNumber}

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day${dayNumber}Test {
    private val input = this::class.java.classLoader.getResource(\"./day${dayNumber}input.txt\")!!.readText()

    @Test
    fun testDay${dayNumber}Part01SampleInput() {

    }

    @Test
    fun testDay${dayNumber}Part01() {

    }

    @Test
    fun testDay${dayNumber}Part02SampleInput() {

    }

    @Test
    fun testDay${dayNumber}Part02() {

    }
}" > $testFile
