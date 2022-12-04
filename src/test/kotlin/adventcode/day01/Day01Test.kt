package adventcode.day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun testDay01Part01() {
        val input = this::class.java.classLoader.getResource("./day01input.txt")!!.readText()
        val result = day01(input, 1)
        assertEquals(71924, result)
    }

    @Test
    fun testDay01Part02() {
        val input = this::class.java.classLoader.getResource("./day01input.txt")!!.readText()
        val result = day01(input, 3)
        assertEquals(210406, result)
    }
}