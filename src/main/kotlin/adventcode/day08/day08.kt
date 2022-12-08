package adventcode.day08

fun day08Part01(input: String): Int {
    val grid = getGrid(input)
    val xCoords = 0..grid[0].lastIndex
    val yCoords = 0..grid.lastIndex
    return xCoords.flatMap { x ->
        yCoords.map { y ->
            isTreeVisible(x, y, grid)
        }
    }.filter { it }.size
}

fun day08Part02(input: String): Int {
    val xLines = getGrid(input)
    val yLines = (0..xLines.lastIndex).map { i -> xLines.map { it[i] } }
    // Can assume it's a square
    val xViewDistances = xLines.map { xLine -> List(xLine.size) { i -> treeViewDistance(i, xLine) } }
    val yViewDistances = yLines.map { yLine -> List(yLine.size) { i -> treeViewDistance(i, yLine) } }

    val zipped = xViewDistances.flatMapIndexed { yIndex, xList ->
        xList.mapIndexed { xIndex, xVals ->
            val correspondingYPair = yViewDistances[xIndex][yIndex]
            listOf(xVals.first, xVals.second, correspondingYPair.first, correspondingYPair.second)
        }
    }

    val scenicScore: (List<Int>) -> Int = { it.reduce { acc, elem -> acc * elem } }
    return zipped.maxOf { scenicScore(it) }
}

private fun getGrid(input: String) = input.split('\n')
    .map { it.chunked(1).map { Integer.parseInt(it) } }

internal fun treeViewDistance(index: Int, treeLine: List<Int>): Pair<Int, Int> {
    val thisTree = treeLine[index]
    val viewForwards = treeLine.slice(index + 1..treeLine.lastIndex)
    val viewBackwards = treeLine.slice(0 until index).reversed()
    val blockingTreeForwards = blockingTreeIndexInclusive(thisTree, viewForwards) ?: viewForwards.size
    val blockingTreeBackwards = blockingTreeIndexInclusive(thisTree, viewBackwards) ?: viewBackwards.size
    return Pair(blockingTreeForwards, blockingTreeBackwards)
}

private fun blockingTreeIndexInclusive(thisTree: Int, treeLine: List<Int>): Int? {
    val blockingTreeIndices = treeLine
        .mapIndexed { i, tree -> if (tree >= thisTree) i else null }
        .filterNotNull()
    // add 1 because we include the blocking tree
    return if (blockingTreeIndices.isEmpty()) return null else blockingTreeIndices.first() + 1
}

private fun isTreeVisible(x: Int, y: Int, input: List<List<Int>>): Boolean {
    // trees on the edge
    if (x == 0 || x == input[0].lastIndex) return true
    if (y == 0 || y == input.lastIndex) return true

    val yLine = input.map { it[x] }
    val thisTree = input[y][x]

    val treesToWest = input[y].slice(0 until x)
    val treesToEast = input[y].slice(x + 1..input[0].lastIndex)
    val treesToNorth = yLine.slice(0 until y)
    val treesToSouth = yLine.slice(y + 1..yLine.lastIndex)

    return listOf(treesToWest, treesToEast, treesToNorth, treesToSouth)
        .map { greaterThanRestOfLine(thisTree, it) }.any { it }
}

private fun greaterThanRestOfLine(tree: Int, restOfLine: List<Int>) = restOfLine.none { it >= tree }
