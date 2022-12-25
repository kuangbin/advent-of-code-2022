fun main() {
  fun part1(input: List<String>): String {
    var sum = 0L
    for (s in input) {
      sum += getValue(s)
    }
    return getSNAFU(sum)
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day25_test")
  check(part1(testInput) == "2=-1=0")

  val input = readInput("Day25")
  println(part1(input))
  println(part2(input))
}

fun getValue(str: String): Long {
  var ret = 0L
  for (cc in str) {
    ret *= 5
    if (cc == '0') {
      continue
    } else if (cc == '1') {
      ret += 1
    } else if (cc == '2') {
      ret += 2
    } else if (cc == '-') {
      ret -= 1
    } else if (cc == '=') {
      ret -= 2
    }
  }
  return ret
}

fun getSNAFU(value: Long): String {
  var ans = ""
  var tmp = value
  while (tmp > 0) {
    if (tmp % 5 <= 2) {
      ans = ('0' + (tmp%5).toInt()) + ans
      tmp /= 5
    } else {
      if (tmp%5 == 3L) {
        ans = "=$ans"
        tmp /= 5
        tmp++
      } else {
        ans = "-$ans"
        tmp /= 5
        tmp++
      }
    }
  }
  return ans
}