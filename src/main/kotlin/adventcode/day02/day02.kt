package adventcode.day02

private enum class Move(val pointsValue: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

private enum class Outcome(val pointsValue: Int) {
    LOSE(0),
    DRAW(3),
    WIN(6)
}

fun day02Part01(input: String): Int {
    val moves = input.split('\n')
        .filter { it != "" } // newline at end of file
        .map { it.split(" ") }
        .map { Pair(it[0].toMove(), it[1].toMove()) }
    val myPointsPerRound: List<Int> = moves.map {
        var total = it.second.pointsValue
        if (whatBeats(it.first) == it.second) total += Outcome.WIN.pointsValue
        if (it.second == it.first) total += Outcome.DRAW.pointsValue
        total
    }
    return myPointsPerRound.reduce { acc, next -> acc + next }
}

fun day02Part02(input: String): Int {
    val events = input.split('\n')
        .filter { it != "" } // newline at end of file
        .map { it.split(" ") }
        .map { Pair(it[0].toMove(), it[1].toOutcome()) }
    val myPointsPerRound: List<Int> = events.map {
        var total = it.second.pointsValue
        if (it.second == Outcome.DRAW) total += it.first.pointsValue
        if (it.second == Outcome.LOSE) {
            total += when (it.first) {
                Move.ROCK -> Move.SCISSORS.pointsValue
                Move.SCISSORS -> Move.PAPER.pointsValue
                else -> Move.ROCK.pointsValue
            }
        }
        if (it.second == Outcome.WIN) total += whatBeats(it.first).pointsValue
        total
    }
    return myPointsPerRound.reduce { acc, next -> acc + next }
}

private fun String.toOutcome() = when (this) {
    "X" -> Outcome.LOSE
    "Y" -> Outcome.DRAW
    "Z" -> Outcome.WIN
    else -> throw Exception("Unknown outcome string $this")
}

private fun String.toMove(): Move {
    if (this == "A" || this == "X") return Move.ROCK
    if (this == "B" || this == "Y") return Move.PAPER
    if (this == "C" || this == "Z") return Move.SCISSORS
    throw Exception("Unexpected move string rep $this")
}

private fun whatBeats(move: Move): Move {
    return when (move) {
        Move.ROCK -> Move.PAPER
        Move.PAPER -> Move.SCISSORS
        Move.SCISSORS -> Move.ROCK
    }
}
