package adventcode.day03

fun day03Part01(input: String): Int {
    val rucksacks = input.split('\n').filter { it != "" }
    val compartmentalised = rucksacks.map {
        Pair(it.substring(0, it.length / 2), it.substring(it.length / 2, it.length))
    }
    val commonItemCount: List<Pair<Char, Int>> = compartmentalised.map { rucksack ->
        val duplicateItem = findDuplicateItemInRucksack(rucksack)
        if (duplicateItem == null) null else
            Pair(duplicateItem, rucksack.second.count { it == duplicateItem })
    }.filterNotNull()
    return commonItemCount.map { it.first.value() }.reduce { acc, item -> acc + item }
}

fun day03Part02(input: String): Int {
    val rucksacks = input.split('\n').filter { it != "" }
    val grouped = rucksacks.mapIndexed { i, r ->
        if (i % 3 == 0) Triple(r, rucksacks[i + 1], rucksacks[i + 2]) else null
    }.filterNotNull()
    val duplicateItems = grouped.map(::findDuplicateItemInGroup)
    return duplicateItems.map { it.value() }.reduce { acc, item -> acc + item }
}

private fun findDuplicateItemInRucksack(rucksack: Pair<String, String>): Char? {
    rucksack.first.map { item -> if (rucksack.second.contains(item)) return item }
    return null
}

private fun findDuplicateItemInGroup(group: Triple<String, String, String>): Char {
    group.first.forEach { firstRucksackItem ->
        if (group.second.contains(firstRucksackItem) && group.third.contains(firstRucksackItem))
            return firstRucksackItem
    }
    throw Exception("No duplicates found in group $group")
}

private fun Char.value(): Int {
    val upperCaseRegex = """[A-Z]""".toRegex()
    val lowerCaseRegex = """[a-z]""".toRegex()
    val stringRep = this.toString()
    if (upperCaseRegex.containsMatchIn(stringRep)) {
        return this.code - 38
    }
    if (lowerCaseRegex.containsMatchIn(stringRep)) {
        return this.code - 96
    }
    throw Exception("Char $this not found in range")
}
