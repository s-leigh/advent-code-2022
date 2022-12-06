package adventcode.day06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {
    private val input = this::class.java.classLoader.getResource("./day06input.txt")!!.readText()
    private val sampleInputsStartOfPacket = hashMapOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11
    )
    private val sampleInputsStartOfMessage = hashMapOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
        "nppdvjthqldpwncqszvftbrmjlhg" to 23,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26
    )

    @Test
    fun testDay06Part01SampleInput() {
        sampleInputsStartOfPacket.forEach {
            assertEquals(it.value, day06Part01(it.key))
        }
    }

    @Test
    fun testDay06Part01() {
        assertEquals(1804, day06Part01(input))
    }

    @Test
    fun testDay06Part02SampleInput() {
        sampleInputsStartOfMessage.forEach {
            assertEquals(it.value, day06Part02(it.key))
        }
    }

    @Test
    fun testDay06Part02() {
        assertEquals(2508, day06Part02(input))
    }
}
