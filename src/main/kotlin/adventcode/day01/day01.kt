package adventcode.day01

fun day01(input: String, sumOfTopX: Int): Int {
    val splitInput = input.split('\n')
    val blankLineIndices = getBlankLineIndices(splitInput)
    val elvesTotalMeals = getTotalMeals(splitInput, blankLineIndices)
    return totalOfTopXElves(elvesTotalMeals, sumOfTopX)
}

private fun getBlankLineIndices(input: List<String>): List<Int> {
    val blankLineIndices = mutableListOf<Int>()
    input.forEachIndexed { i, s ->
        if (s == "") blankLineIndices.add(i)
    }
    return blankLineIndices
}

private fun getTotalMeals(input: List<String>, blankLineIndices: List<Int>): List<Int> {
    val individualMeals = blankLineIndices.mapIndexed { i, blankLineIndex ->
        val start = if (i == 0) 0 else blankLineIndices[i - 1] + 1 // skip blank line itself
        input.subList(start, blankLineIndex).map { Integer.parseInt(it) }
    }
    return individualMeals.map { elf ->
        elf.reduce { acc, next -> acc + next }
    }
}

private fun totalOfTopXElves(list: List<Int>, topX: Int): Int =
    list.sortedDescending().subList(0, topX).reduce { acc, next -> acc + next }
