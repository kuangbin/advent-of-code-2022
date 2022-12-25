fun main() {
  fun part1(input: List<String>): Int {
    var positions = buildSet{
      for (i in 0 until input.size) {
        for (j in 0 until input[i].length) {
          if (input[i][j] == '#') {
            add(i to j)
          }
        }
      }
    }

    for (i in 0 until 10) {
      positions = gao(positions, i)
    }

    val minX = positions.minBy { it.first }.first
    val maxX = positions.maxBy { it.first }.first
    val minY = positions.minBy { it.second }.second
    val maxY = positions.maxBy { it.second }.second
    return (maxX - minX + 1) * (maxY - minY + 1) - positions.size
  }

  fun part2(input: List<String>): Int {
    var positions = buildSet{
      for (i in 0 until input.size) {
        for (j in 0 until input[i].length) {
          if (input[i][j] == '#') {
            add(i to j)
          }
        }
      }
    }

    var ii = 0
    while (true) {

      var stable = true
      for (position in positions) {
        var num = 0
        for (i in -1..1) {
          for (j in -1..1) {
            if (i == 0 && j == 0) {
              continue
            }
            if ((position.first+i to position.second+j) in positions) {
              num++
            }
          }
        }
        if (num != 0) {
          stable = false
          break
        }
      }
      if (stable) {
        break
      }

      positions = gao(positions, ii)
      ii++
    }

    return ii+1
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day23_test")
  check(part1(testInput) == 110)
  println(part2(testInput))

  val input = readInput("Day23")
  println(part1(input))
  println(part2(input))
}

val dir0 = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
val dir1 = listOf(-1 to -1, 1 to -1, -1 to -1, -1 to 1)
val dir2 = listOf(-1 to 1, 1 to 1, 1 to -1, 1 to 1)

fun gao(positions: Set<Pair<Int, Int>>, st: Int): Set<Pair<Int, Int>> {
  val mp = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
  for (position in positions) {
    val x = position.first
    val y = position.second
    var numOfAround = 0
    for (i in -1..1) {
      for (j in -1..1) {
        if (i == 0 && j == 0) {
          continue
        }
        if (positions.contains(x+i to y+j)) {
          numOfAround++
        }
      }
    }
    if (numOfAround == 0) {
      mp[position] = position
      continue
    }
    for (i in 0 until 4) {
      val x1 = x + dir0[(i+st)%4].first
      val y1 = y + dir0[(i+st)%4].second
      val x2 = x + dir1[(i+st)%4].first
      val y2 = y + dir1[(i+st)%4].second
      val x3 = x + dir2[(i+st)%4].first
      val y3 = y + dir2[(i+st)%4].second
      if ((x1 to y1) !in positions && (x2 to y2) !in positions && (x3 to y3) !in positions) {
        mp[position] = x1 to y1
        break
      }
    }
    if (!mp.contains(position)) {
      mp[position] = position
    }
  }
  val posNum = mutableMapOf<Pair<Int, Int>, Int>()
  for (position in positions) {
    posNum[mp[position]!!] = posNum.getOrDefault(mp[position], 0) + 1
  }
  return buildSet {
    for (position in positions) {
      if (posNum[mp[position]] == 1) {
        add(mp[position]!!)
      } else {
        add(position)
      }
    }
  }
}
