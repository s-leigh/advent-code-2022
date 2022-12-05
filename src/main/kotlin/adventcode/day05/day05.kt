package adventcode.day05

private data class Instruction(
    val count: Int,
    val from: Int,
    val to: Int
) {
    private val fromIndex = this.from - 1
    private val toIndex = this.to - 1

    fun popAndPush(stacks: List<MutableList<String>>) {
        (1..this.count).forEach { _ ->
            stacks[toIndex].add(stacks[fromIndex].last())
            stacks[fromIndex].removeLast()
        }
    }

    fun moveBatches(stacks: List<MutableList<String>>) {
        val fromStack = stacks[fromIndex]
        val sublistMoveFromIndex = fromStack.lastIndex - this.count + 1
        val sublistMoveToIndex = fromStack.lastIndex + 1

        val itemsToMove = fromStack.subList(sublistMoveFromIndex, sublistMoveToIndex)
        stacks[toIndex].addAll(itemsToMove)
        stacks[fromIndex].subList(sublistMoveFromIndex, sublistMoveToIndex).clear()
    }
}

fun day05(input: String, batchMove: Boolean): String {
    val listInput = input.split('\n')
    val stacksAndInstructionsSeparatorIndex = listInput.indexOf("")
    val initialRows = listInput.subList(0, stacksAndInstructionsSeparatorIndex)
    val stacks = parseStacks(initialRows)
    val instructions = parseInstructions(listInput.subList(stacksAndInstructionsSeparatorIndex + 1, listInput.size))

    instructions.forEach { if (batchMove) it.moveBatches(stacks) else it.popAndPush(stacks) }
    return topOfStacks(stacks)
}

private fun topOfStacks(stacks: List<List<String>>): String =
    stacks.filter { it.isNotEmpty() }.map { it.last() }.reduce { acc, elem -> acc + elem }

private fun parseInstructions(instructions: List<String>): List<Instruction> =
    instructions
        .filterNot { it == "" }
        .map {
            it.split("""move|from|to""".toRegex())
                .filter { it != "" }
                .map { it.trim() }
                .map { Integer.parseInt(it) }
        }.map { Instruction(it[0], it[1], it[2]) }

private fun parseStacks(initialRows: List<String>): List<MutableList<String>> {
    val numberOfStacks =
        Integer.parseInt(initialRows.last()[initialRows.last().length - 1].toString()) // hacky way to get this
    val stackList = (1..numberOfStacks).map { _ -> mutableListOf<String>() }

    initialRows.forEach { stackString ->
        stackString.forEachIndexed { i, char ->
            if (char == '[') stackList[i / 4].add(0, stackString[i + 1].toString())
        }
    }
    return stackList
}
