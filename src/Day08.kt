import kotlin.math.max

fun main() {
  fun part1(input: List<String>): Int {
    val n = input.size
    val m = input[0].length
    val array = Array(n) { BooleanArray(m) }
    for (i in 0 until n) {
      for (j in 0 until m) {
        array[i][j] = false
      }
    }
    for (i in 0 until n) {
      var maxValue = -1
      for (j in 0 until m) {
        val v = input[i][j] - '0'
        if (v > maxValue) {
          array[i][j] = true
          maxValue = v
        }
      }
      maxValue = -1
      for (j in m-1 downTo 0) {
        val v = input[i][j] - '0'
        if (v > maxValue) {
          array[i][j] = true
          maxValue = v
        }
      }
    }
    for (j in 0 until m) {
      var maxValue = -1
      for (i in 0 until n) {
        val v = input[i][j] - '0'
        if (v > maxValue) {
          array[i][j] = true
          maxValue = v
        }
      }
      maxValue = -1
      for (i in n-1 downTo 0) {
        val v = input[i][j] - '0'
        if (v > maxValue) {
          array[i][j] = true
          maxValue = v
        }
      }
    }
    var ans = 0
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (array[i][j]) {
          ans++
        }
      }
    }
    return ans
  }

  fun part2(input: List<String>): Int {
    val n = input.size
    val m = input[0].length
    var ans = 0
    for (i in 0 until n) {
      for (j in 0 until m) {
        var t1 = 0
        for (x in i-1 downTo 0) {
          t1++
          if (input[x][j] >= input[i][j]) break
        }
        var t2 = 0
        for (x in i+1 until n) {
          t2++
          if (input[x][j] >= input[i][j]) break
        }
        var t3 = 0
        for (y in j-1 downTo 0) {
          t3++
          if (input[i][y] >= input[i][j]) break
        }
        var t4 = 0
        for (y in j+1 until m) {
          t4++
          if (input[i][y] >= input[i][j]) break
        }
        ans = max(ans, t1*t2*t3*t4)
      }
    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day08_test")
  check(part1(testInput) == 21)
  check(part2(testInput) == 8)

  val input = readInput("Day08")
  println(part1(input))
  println(part2(input))
}