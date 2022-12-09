package adventcode.day09

import kotlin.math.abs

private typealias x = Int
private typealias y = Int

fun day09Part01(input: String): Int {
    val instructions = getInstructions(input)
    val visitedTailCoords = visitedTailCoordinates(instructions, 2)
    return visitedTailCoords.size
}

fun day09Part02(input: String): Int {
    val instructions = getInstructions(input)
    val visitedTailCoords = visitedTailCoordinates(instructions, 10)
    return visitedTailCoords.size
}

private fun visitedTailCoordinates(instructions: List<Pair<String, Int>>, ropeSize: Int): List<Pair<x, y>> {
    var rope = List(ropeSize) { Pair(0, 0) }
    val visitedTailCoords = instructions.flatMap { instruction ->
        (0 until instruction.second).map {
            val newHeadCoords = newHeadCoords(instruction.first, rope[0])
            rope = newRopePositions(rope.slice(1..rope.lastIndex), listOf(newHeadCoords))
            rope.last()
        }
    }.distinct()
    return visitedTailCoords
}

private fun getInstructions(input: String): List<Pair<String, Int>> =
    input.split('\n')
        .map { it.split(' ') }
        .map { Pair(it[0], Integer.parseInt(it[1])) }

internal fun newRopePositions(tail: List<Pair<x, y>>, head: List<Pair<x, y>>): List<Pair<x, y>> {
    if (tail.size == 1) return head.plus(newKnotCoords(head.last(), tail.single()))
    val newCoords = newKnotCoords(head.last(), tail.first())
    return newRopePositions(tail.slice(1..tail.lastIndex), head.plus(newCoords))
}

private fun newHeadCoords(instructionDirection: String, headStart: Pair<x, y>): Pair<x, y> =
    when (instructionDirection) {
        "R" -> Pair(headStart.first + 1, headStart.second)
        "L" -> Pair(headStart.first - 1, headStart.second)
        "U" -> Pair(headStart.first, headStart.second + 1)
        "D" -> Pair(headStart.first, headStart.second - 1)
        else -> throw Exception("Unrecognised direction $instructionDirection")
    }

internal fun newKnotCoords(leader: Pair<x, y>, knot: Pair<x, y>): Pair<x, y> {
    val xDifference = abs(leader.first - knot.first)
    val yDifference = abs(leader.second - knot.second)
    val mustMoveX = xDifference > 1 || xDifference == 1 && yDifference == 2
    val mustMoveY = yDifference > 1 || yDifference == 1 && xDifference == 2
    if (!mustMoveX && !mustMoveY) return knot

    val newX = if (mustMoveX) {
        if (leader.first > knot.first) knot.first + 1 else knot.first - 1
    } else knot.first
    val newY = if (mustMoveY) {
        if (leader.second > knot.second) knot.second + 1 else knot.second - 1
    } else knot.second

    return Pair(newX, newY)
}
