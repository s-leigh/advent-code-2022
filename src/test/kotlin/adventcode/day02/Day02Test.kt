package adventcode.day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun testDay02Part01() {
        val input = this::class.java.classLoader.getResource("./day02input.txt")!!.readText()
        val result = day02Part01(input)
        assertEquals(15691, result)
    }

    @Test
    fun testDay02Part02Example() {
        val input = "A Y\nB X\nC Z\n"
        val result = day02Part02(input)
        assertEquals(12, result)
    }

    @Test
    fun testDay02Part02() {
        val input = this::class.java.classLoader.getResource("./day02input.txt")!!.readText()
        val result = day02Part02(input)
        assertEquals(12989, result)
    }
}