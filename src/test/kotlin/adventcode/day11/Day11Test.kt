package adventcode.day11

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    private val input = this::class.java.classLoader.getResource("./day11input.txt")!!.readText()
    private val sampleInput = this::class.java.classLoader.getResource("./day11SampleInput.txt")!!.readText()

    @Test
    fun testDay11Part01SampleInput() {
        assertEquals(10605, day11Part01(sampleInput))
    }

    @Test
    fun testDay11Part01() {
        assertEquals(117624, day11Part01(input))
    }

    @Test
    fun testDay11Part02SampleInput() {
        assertEquals(2713310158L, day11Part02(sampleInput))
    }

    @Test
    fun testDay11Part02() {
        assertEquals(16792940265, day11Part02(input))

    }
}
