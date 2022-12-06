package adventcode.day06

fun day06Part01(input: String): Int = endOfUniqueWindowIndex(input, 4)

fun day06Part02(input: String): Int = endOfUniqueWindowIndex(input, 14)

private fun endOfUniqueWindowIndex(str: String, window: Int): Int {
    for (i in window until str.length) {
        if (str.substring(i - window, i).toSet().size == window) return i
    }
    throw Exception("No unique pattern found!")
}
