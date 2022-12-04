package adventcode.day04

fun day04Part01(input: String): Int {
    val assignments = parseInput(input)
    val nestedAssignments = assignments.filter { it.first fullyContains it.second || it.second fullyContains it.first }
    return nestedAssignments.size
}

fun day04Part02(input: String): Int {
    val assignments = parseInput(input)
    val overlappingAssignments = assignments.filter { it.first overlapsWith it.second }
    return overlappingAssignments.size
}

private fun parseInput(input: String): List<Pair<IntRange, IntRange>> =
    input.split('\n')
        .filter { it != "" } // newline at end of file
        .map { it.split(",", "-") }
        .map { it.map { Integer.parseInt(it) } }
        .map { Pair(it[0]..it[1], it[2]..it[3]) }

private infix fun IntRange.fullyContains(intRange: IntRange): Boolean =
    this.first() <= intRange.first() && this.last() >= intRange.last()

private infix fun IntRange.overlapsWith(intRange: IntRange): Boolean =
    (this.first <= intRange.first && this.last >= intRange.first)
            || (intRange.first <= this.first && intRange.last >= this.first)
