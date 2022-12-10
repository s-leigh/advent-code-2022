package adventcode.day10

private const val VIEWPORT_HEIGHT = 6
private const val VIEWPORT_WIDTH = 40

private typealias Cycle = Int
private typealias XValue = Int

fun day10Part01(input: String): Int {
    val addXValuesOrNull = addXValuesOrNull(input)
    val cycles = beginningCycleValues(addXValuesOrNull)

    val significantCycles = (1..cycles.maxOf { it.key })
        .filter { it == 20 || (it + 20) % 40 == 0 }
        .map { cycles.getClosest(it) }

    return significantCycles.toList().fold(0) { acc, elem ->
        val cycleMultiplier = if (elem.first == 19 || ((elem.first + 20) % 40 != 0)) elem.first + 1 else elem.first
        acc + (cycleMultiplier * elem.second)
    }
}

fun day10Part02(input: String): String {
    val addXValuesOrNull = addXValuesOrNull(input)
    val cycles = beginningCycleValues(addXValuesOrNull)

    val strRep = (0 until VIEWPORT_HEIGHT * VIEWPORT_WIDTH).map { pixelIndex ->
        val relevantCycle = cycles.getClosest(pixelIndex + 1)
        val spriteValues = relevantCycle.second - 1..relevantCycle.second + 1
        if (spriteValues.contains(pixelIndex % VIEWPORT_WIDTH)) "#" else "."
    }.mapIndexed { i, elem ->
        if ((i + 1) % VIEWPORT_WIDTH == 0) "$elem\n"
        else elem
    }.joinToString("")

    println(strRep)
    return strRep
}

private fun addXValuesOrNull(input: String) = input.split('\n')
    .map { line -> if (line == "noop") null else Integer.parseInt(line.split(' ')[1]) }

// Values are at the BEGINNING of the cycle (end of cycle values would initialise with Pair(0, 1))
private fun beginningCycleValues(addXValuesOrNull: List<Int?>) =
    addXValuesOrNull.fold(listOf<Pair<Cycle, XValue>>(Pair(1, 1))) { acc, elem ->
        if (elem == null) acc.plus(Pair(acc.last().first + 1, acc.last().second))
        else acc.plus(Pair(acc.last().first + 2, acc.last().second + elem))
    }.toMap()

// Get entry with this key or the one before
private fun Map<Cycle, XValue>.getClosest(maybeKey: Int): Pair<Cycle, XValue> =
    if (this.contains(maybeKey)) Pair(maybeKey, this[maybeKey]!!)
    else Pair(maybeKey, this[maybeKey - 1]!!)
