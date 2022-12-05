package adventcode.day05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Test {
    private val input = this::class.java.classLoader.getResource("./day05input.txt")!!.readText()
    private val sampleInput = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""

    @Test
    fun testDay05Part01SampleInput() {
        val result = day05(sampleInput, false)
        assertEquals("CMZ", result)
    }

    @Test
    fun testDay05Part01() {
        val result = day05(input, false)
        assertEquals("JRVNHHCSJ", result)
    }

    @Test
    fun testDay05Part02SampleInput() {
        val result = day05(sampleInput, true)
        assertEquals("MCD", result)
    }

    @Test
    fun testDay05Part02() {
        val result = day05(input, true)
        assertEquals("GNFBSBJLH", result)
    }
}
