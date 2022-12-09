package adventcode.day09

import kotlin.math.abs

private typealias x = Int
private typealias y = Int

fun day09Part01(input: String): Int {
    val instructions = getInstructions(input)
    val rope = List(2) { Pair(0, 0) }
    val visitedTailCoords = visitedTailCoordinates(instructions, rope)
    return visitedTailCoords.size
}

fun day09Part02(input: String): Int {
    val instructions = getInstructions(input)
    val rope = List(10) { Pair(0, 0) }
    val visitedTailCoords = visitedTailCoordinates(instructions, rope)
    return visitedTailCoords.size
}

// Parse instructions as list of single directions, e.g. "U 4" becomes {'U', 'U', 'U', 'U'}
private fun getInstructions(input: String): CharArray =
    input.split('\n')
        .map { it.split(' ') }
        .map { Pair(it[0], Integer.parseInt(it[1])) }
        .map { it.first.repeat(it.second).toCharArray() }
        .reduce { acc, elem -> acc.plus(elem) }

private tailrec fun visitedTailCoordinates(
    instructions: CharArray,
    rope: List<Pair<x, y>>,
    visitedTailCoords: List<Pair<x, y>> = listOf()
): List<Pair<x, y>> {
    if (instructions.isEmpty()) return visitedTailCoords.distinct()
    val newHeadCoords = newHeadCoords(instructions[0], rope[0])
    val newRopePosition = newRopePosition(listOf(newHeadCoords), rope.slice(1..rope.lastIndex))
    return visitedTailCoordinates(
        instructions.sliceArray(1..instructions.lastIndex),
        newRopePosition,
        visitedTailCoords.plus(newRopePosition.last())
    )
}

private fun newHeadCoords(instructionDirection: Char, headStart: Pair<x, y>): Pair<x, y> =
    when (instructionDirection) {
        'R' -> Pair(headStart.first + 1, headStart.second)
        'L' -> Pair(headStart.first - 1, headStart.second)
        'U' -> Pair(headStart.first, headStart.second + 1)
        'D' -> Pair(headStart.first, headStart.second - 1)
        else -> throw Exception("Unrecognised direction $instructionDirection")
    }

internal tailrec fun newRopePosition(head: List<Pair<x, y>>, tail: List<Pair<x, y>>): List<Pair<x, y>> {
    if (tail.size == 1) return head.plus(newKnotCoords(head.last(), tail.single()))
    val newCoords = newKnotCoords(head.last(), tail.first())
    return newRopePosition(head.plus(newCoords), tail.slice(1..tail.lastIndex))
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
