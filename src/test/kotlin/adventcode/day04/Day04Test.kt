package adventcode.day04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {
    private val input = this::class.java.classLoader.getResource("./day04input.txt")!!.readText()
    private val sampleInput = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""

    @Test
    fun testDay04Part01SampleInput() {
        val result = day04Part01(sampleInput)
        assertEquals(2, result)
    }

    @Test
    fun testDay04Part01() {
        val result = day04Part01(input)
        assertEquals(500, result)
    }

    @Test
    fun testDay04Part02SampleInput() {
        val result = day04Part02(sampleInput)
        assertEquals(4, result)
    }

    @Test
    fun testDay04Part02() {
        val result = day04Part02(input)
        assertEquals(815, result)
    }
}