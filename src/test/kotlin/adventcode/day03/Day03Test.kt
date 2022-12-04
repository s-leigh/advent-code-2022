package adventcode.day03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {
    private val input = this::class.java.classLoader.getResource("./day03input.txt")!!.readText()

    @Test
    fun testDay03Part01SampleInput() {
        val sampleInput = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""
        val result = day03Part01(sampleInput)
        assertEquals(157, result)
    }

    @Test
    fun testDay03Part01() {
        val result = day03Part01(input)
        assertEquals(7848, result)
    }

    @Test
    fun testDay03Part02SampleInput() {
        val sampleInput = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""
        val result = day03Part02(sampleInput)
        assertEquals(70, result)
    }

    @Test
    fun testDay03Part02() {
        val result = day03Part02(input)
        assertEquals(2616, result)
    }
}