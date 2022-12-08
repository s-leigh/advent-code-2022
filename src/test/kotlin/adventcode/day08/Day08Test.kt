package adventcode.day08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {
    private val input = this::class.java.classLoader.getResource("./day08input.txt")!!.readText()
    private val sampleInput = """30373
25512
65332
33549
35390"""

    @Test
    fun testDay08Part01SampleInput() {
        assertEquals(21, day08Part01(sampleInput))
    }

    @Test
    fun testDay08Part01() {
        assertEquals(1818, day08Part01(input))
    }

    @Test
    fun testDay08Part02SampleInput() {
        assertEquals(8, day08Part02(sampleInput))
    }

    @Test
    fun testDay08Part02() {
        assertEquals(368368, day08Part02(input))
    }

    @Test
    fun `Tree view distance example 1`() {
        val xInput = listOf(2, 5, 5, 1, 2)
        assertEquals(Pair(2, 1), treeViewDistance(2, xInput))
        val yInput = listOf(3, 5, 3, 5, 3)
        assertEquals(Pair(2, 1), treeViewDistance(1, yInput))
    }

    @Test
    fun `Tree view distance example 2`() {
        val xInput = listOf(3, 3, 5, 4, 9)
        assertEquals(Pair(2, 2), treeViewDistance(2, xInput))
        val yInput = listOf(3, 5, 3, 5, 3)
        assertEquals(Pair(1, 2), treeViewDistance(3, yInput))
    }
}
