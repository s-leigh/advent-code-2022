package adventcode.day07

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {
    private val input = this::class.java.classLoader.getResource("./day07input.txt")!!.readText()
    private val sampleInput = """${'$'} cd /
${'$'} ls
dir a
14848514 b.txt
8504156 c.dat
dir d
${'$'} cd a
${'$'} ls
dir e
29116 f
2557 g
62596 h.lst
${'$'} cd e
${'$'} ls
584 i
${'$'} cd ..
${'$'} cd ..
${'$'} cd d
${'$'} ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"""

    @Test
    fun testDay07Part01SampleInput() {
        assertEquals(95437, day07Part01(sampleInput))
    }

    @Test
    fun testDay07Part01() {
        assertEquals(1749646, day07Part01(input))
    }

    @Test
    fun testDay07Part02SampleInput() {
        assertEquals(24933642, day07Part02(sampleInput))
    }

    @Test
    fun testDay07Part02() {
        assertEquals(1498966, day07Part02(input))
    }
}
