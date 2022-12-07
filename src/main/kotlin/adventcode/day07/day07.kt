package adventcode.day07

private typealias Line = List<String>
private typealias DirectoryKey = String
private typealias FileSize = Long
private typealias FilePath = List<String>

private data class LsCommandOutput(
    val dirPath: List<String>,
    val output: List<Line>
)

fun day07Part01(input: String): Long {
    val dirSizes = getDirSizes(input)
    return dirSizes.values.filter { it <= 100000 }.sum()
}

fun day07Part02(input: String): Long {
    val dirSizes = getDirSizes(input)
    val freeSpace = 70000000 - dirSizes["/"]!!
    val requiredCleanUp = 30000000 - freeSpace
    return dirSizes.filter { it.value >= requiredCleanUp }.minBy { it.value }.value
}

private fun getDirSizes(input: DirectoryKey): HashMap<DirectoryKey, FileSize> {
    val parsedInput = input.split('\n').map { it.split(' ') }
    val lsCommands = getLsCommands(parsedInput)

    val dirSizes: HashMap<DirectoryKey, FileSize> = hashMapOf()
    lsCommands.forEach { ls ->
        val dirSize = ls.output.filterNot { it.isDirectory() }.sumOf { it.fileSize() }
        dirSizes.recordDirSize(ls.dirPath, dirSize)
    }
    return dirSizes
}

private fun HashMap<DirectoryKey, FileSize>.recordDirSize(path: FilePath, fileSize: FileSize) {
    if (path.isEmpty() || path.size == 1) {
        this.addOrCreate("/", fileSize)
        return
    }
    val key = path.joinToString("/")
    this.addOrCreate(key, fileSize)
    val nextLevelUp = path.subList(0, path.lastIndex)
    this.recordDirSize(nextLevelUp, fileSize)
}

private fun HashMap<DirectoryKey, FileSize>.addOrCreate(key: DirectoryKey, value: FileSize) {
    if (this[key] == null) this[key] = value else this[key] = this[key]!! + value
}

private fun getLsCommands(
    parsedInput: List<Line>,
    dirTracker: FilePath = listOf(),
    lsCommands: List<LsCommandOutput> = listOf()
): List<LsCommandOutput> {
    if (parsedInput.isEmpty()) return lsCommands
    val fromNextLine = parsedInput.subList(1, parsedInput.size)
    // in case a dir is called cd or ls
    if (!parsedInput[0].isCommand()) return getLsCommands(fromNextLine, dirTracker, lsCommands)

    return when (val command = parsedInput[0][1]) {
        "cd" -> {
            val cdTarget = parsedInput.commandTarget()
            val newDirTracker = if (cdTarget == "..") {
                dirTracker.subList(0, dirTracker.lastIndex)
            } else dirTracker.plus(cdTarget)
            getLsCommands(fromNextLine, newDirTracker, lsCommands)
        }

        "ls" -> {
            val nextCommandIndex = parsedInput
                .subList(1, parsedInput.lastIndex)
                .map { it.isCommand() }
                .indexOfFirst { it } + 1
            val endSegmentIndex = if (nextCommandIndex == 0) parsedInput.size else nextCommandIndex
            val lsCommandOutput = LsCommandOutput(dirTracker, parsedInput.subList(1, endSegmentIndex))
            getLsCommands(
                parsedInput.subList(endSegmentIndex, parsedInput.size),
                dirTracker,
                lsCommands.plus(lsCommandOutput)
            )
        }

        else -> throw Exception("Unrecognised command $command")
    }
}

private fun Line.isCommand() = this[0] == "$"

private fun Line.isDirectory() = this[0] == "dir"

private fun Line.fileSize() = this[0].toLong()

private fun List<Line>.commandTarget() = this[0][2]
