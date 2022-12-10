package adventcode.day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    private val input = this::class.java.classLoader.getResource("./day10input.txt")!!.readText()
    private val sampleInput = this::class.java.classLoader.getResource("./day10SampleInput.txt")!!.readText()

    @Test
    fun testDay10Part01SampleInput() {
        assertEquals(13140, day10Part01(sampleInput))
    }

    @Test
    fun testDay10Part01() {
        assertEquals(14160, day10Part01(input))
    }

    @Test
    fun testDay10Part02SampleInput() {
        val expected = """##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....
"""
        assertEquals(expected, day10Part02(sampleInput))
    }

    @Test
    fun testDay10Part02() {
        val expected = """###....##.####.###..###..####.####..##..
#..#....#.#....#..#.#..#.#....#....#..#.
#..#....#.###..#..#.#..#.###..###..#....
###.....#.#....###..###..#....#....#....
#.#..#..#.#....#.#..#....#....#....#..#.
#..#..##..####.#..#.#....####.#.....##..
"""
        // RJERPEFC
        assertEquals(expected, day10Part02(input))
    }
}
