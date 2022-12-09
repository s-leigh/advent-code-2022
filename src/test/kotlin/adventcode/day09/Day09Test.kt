package adventcode.day09

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {
    private val input = this::class.java.classLoader.getResource("./day09input.txt")!!.readText()
    private val sampleInput1 = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2"""
    private val sampleInput2 = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20"""

    @Test
    fun testDay09Part01SampleInput() {
        assertEquals(13, day09Part01(sampleInput1))
    }

    @Test
    fun testDay09Part01() {
        assertEquals(6464, day09Part01(input))
    }

    @Test
    fun testDay09Part02SampleInput1() {
        assertEquals(1, day09Part02(sampleInput1))
    }

    @Test
    fun testDay09Part02SampleInput2() {
        assertEquals(36, day09Part02(sampleInput2))
    }

    @Test
    fun testDay09Part02() {
        assertEquals(2604, day09Part02(input))
    }

    @Nested
    inner class MovementTest {
        @Test
        fun `should move right`() {
            val headCoords = Pair(3, 5)
            val tailCoords = Pair(1, 5)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(2, 5), result)
        }

        @Test
        fun `should move left`() {
            val headCoords = Pair(1, 5)
            val tailCoords = Pair(3, 5)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(2, 5), result)
        }

        @Test
        fun `should move up`() {
            val headCoords = Pair(1, 5)
            val tailCoords = Pair(1, 3)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(1, 4), result)
        }

        @Test
        fun `should move down`() {
            val headCoords = Pair(1, 3)
            val tailCoords = Pair(1, 5)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(1, 4), result)
        }

        @Test
        fun `should move diagonally up-right`() {
            val headCoords = Pair(3, 5)
            val tailCoords = Pair(2, 3)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(3, 4), result)
        }

        @Test
        fun `should move diagonally up-left`() {
            val headCoords = Pair(2, 4)
            val tailCoords = Pair(4, 3)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(3, 4), result)
        }

        @Test
        fun `should move diagonally down-right`() {
            val headCoords = Pair(3, 3)
            val tailCoords = Pair(2, 5)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(3, 4), result)
        }

        @Test
        fun `should move diagonally down-left`() {
            val headCoords = Pair(3, 3)
            val tailCoords = Pair(4, 5)
            val result = newKnotCoords(headCoords, tailCoords)
            assertEquals(Pair(3, 4), result)
        }
    }

    @Nested
    inner class RopeTest {
        @Test
        fun `brings knots to new position`() {
            // sampleInput 1 'up 4' movement 2
            val head = Pair(4, 2)
            val rope = listOf(
                Pair(3, 0),
                Pair(2, 0),
                Pair(1, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0)
            )
            val expected = listOf(
                Pair(4, 2),
                Pair(4, 1),
                Pair(3, 1),
                Pair(2, 1),
                Pair(1, 1),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0)
            )
            assertEquals(expected, newRopePositions(rope, listOf(head)))
        }
    }
}
