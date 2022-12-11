package adventcode.day11

private typealias WorryLevel = Long
private typealias MonkeyIndex = Int
private typealias RulesBlock = List<String>

private data class Monkey(
    private val items: MutableList<WorryLevel>,
    private val monkeyOperation: (WorryLevel) -> WorryLevel,
    private val test: (WorryLevel) -> MonkeyIndex,
    private val worryOperation: (WorryLevel) -> WorryLevel,
    var inspections: Long = 0
) {
    fun inspectItems() {
        this.items.forEachIndexed { i, item ->
            inspections++
            this.items[i] = worryOperation(monkeyOperation(item))
        }
    }

    fun testItems(): List<Pair<Int, MonkeyIndex>> = this.items.mapIndexed { itemIndex, item ->
        Pair(itemIndex, test(item))
    }

    fun throwItems(itemIndicesAndTargetMonkeys: List<Pair<Int, Monkey>>) {
        itemIndicesAndTargetMonkeys.forEach {
            it.second.catchItem(this.items[it.first])
        }
        this.items.clear()
    }

    fun catchItem(worryLevel: WorryLevel) {
        this.items.add(worryLevel)
    }
}

fun day11Part01(input: String): Long {
    val monkeyRulesBlock = rulesBlocks(input)
    val monkeyDivisors = monkeyRulesBlock.getMonkeyDivisors()
    val monkeys = monkeyRulesBlock.mapIndexed { i, chunk ->
        parseMonkey(
            chunk,
            monkeyDivisors[i]
        ) { worryLevel: WorryLevel -> worryLevel / 3 }
    }

    monkeyBusiness(monkeys, 20)

    return monkeys.map { it.inspections }.sorted().takeLast(2).reduce { acc, elem -> acc * elem }
}

fun day11Part02(input: String): Long {
    val monkeyRulesBlock = rulesBlocks(input)
    val monkeyDivisors = monkeyRulesBlock.getMonkeyDivisors()
    val worryModulo = monkeyDivisors.reduce { acc, elem -> acc * elem }
    val worryOperation = { worryLevel: WorryLevel -> worryLevel % worryModulo }

    val monkeys = monkeyRulesBlock.mapIndexed { i, chunk -> parseMonkey(chunk, monkeyDivisors[i], worryOperation) }

    monkeyBusiness(monkeys, 10_000)

    return monkeys.map { it.inspections }.sorted().takeLast(2).reduce { acc, elem -> acc * elem }
}

private fun rulesBlocks(input: String): List<RulesBlock> = input.split('\n')
    .filterNot { it == "" }
    .chunked(6)

private fun List<RulesBlock>.getMonkeyDivisors(): List<Int> =
    this.map { it.filterIndexed { i, _ -> i == 3 } }
        .flatten()
        .map { Integer.parseInt(it.split("divisible by ")[1]) }

private fun monkeyBusiness(monkeys: List<Monkey>, iterations: Int) {
    (0 until iterations).forEach { _ ->
        monkeys.forEach { thisMonkey ->
            thisMonkey.inspectItems()
            val testResults = thisMonkey.testItems()
            val receivingMonkeys = testResults.map { Pair(it.first, monkeys[it.second]) }
            thisMonkey.throwItems(receivingMonkeys)
        }
    }
}

private fun parseMonkey(
    rulesBlock: RulesBlock,
    testDivisor: Int,
    worryOperation: (WorryLevel) -> WorryLevel
): Monkey {
    val instructions = rulesBlock.slice(1..rulesBlock.lastIndex).map { it.trim() }

    val startingItems = instructions[0]
        .split(':')[1]
        .split(',')
        .map { Integer.parseInt(it.trim()).toLong() }
        .toMutableList()

    val operationSegment = instructions[1]
        .slice(instructions[1].indexOfAny(listOf("+", "*"))..instructions[1].lastIndex)
        .split(' ')
    val operation: (WorryLevel) -> WorryLevel =
        if (operationSegment[1] == "old") {
            if (operationSegment[0] == "*") { WorryLevel -> WorryLevel * WorryLevel }
            else { WorryLevel -> WorryLevel + WorryLevel }
        } else {
            val parsedOperand = Integer.parseInt(operationSegment[1])
            if (operationSegment[0] == "*") { WorryLevel -> WorryLevel * parsedOperand }
            else { WorryLevel -> WorryLevel + parsedOperand }
        }

    val targetMonkeyIndexTrue = Integer.parseInt(instructions[3].last().toString())
    val targetMonkeyIndexFalse = Integer.parseInt(instructions[4].last().toString())
    val test = { long: WorryLevel -> if (long % testDivisor == 0L) targetMonkeyIndexTrue else targetMonkeyIndexFalse }

    return Monkey(startingItems, operation, test, worryOperation)
}
