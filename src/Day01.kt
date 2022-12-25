import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var ans = 0
        var tmp = 0
        for (ss in input) {
            if (ss.isEmpty()) {
                ans = max(ans, tmp)
                tmp = 0
                continue
            }
            tmp += ss.toInt()
        }
        ans = max(ans, tmp)
        return ans
    }

    fun part2(input: List<String>): Int {
        var tmp = 0
        val ll = mutableListOf<Int>()
        for (ss in input) {
            if (ss.isEmpty()) {
                ll.add(tmp)
                tmp = 0
                continue
            }
            tmp += ss.toInt()
        }
        ll.add(tmp)
        ll.sortDescending()
        return ll[0] + ll[1] + ll[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    println(part2(testInput))

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
