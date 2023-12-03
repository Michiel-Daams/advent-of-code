package net.daams.solutions

import net.daams.Solution

class `3b`(input: String): Solution(input) {

    val gears: MutableList<List<Int>> = mutableListOf()
    var splitInput: List<String> = listOf()

    override fun run() {
        val gearRatios: MutableList<Int> = mutableListOf()
        splitInput = input.split("\n")
        splitInput.forEach{
            val indices: MutableList<Int> = mutableListOf()
            it.forEachIndexed{ index, s ->
                if (s == '*') indices.add(index)
            }
            gears.add(indices.toList())
        }

        gears.forEachIndexed{ y, xs ->
            xs.forEach {
                val adjacentNumbers: MutableList<Pair<Int, IntRange>> = mutableListOf()
                for (x: Int in it -1 .. it + 1) {
                    if (x < 0 || x > 139) continue
                    for (yy: Int in y - 1 .. y + 1) {
                        if (yy < 0 || y > 139) continue
                        if (splitInput[yy][x].isDigit()) adjacentNumbers.add(getFullDigit(x, yy))
                    }
                }
                if (adjacentNumbers.distinct().size == 2) gearRatios.add(adjacentNumbers.distinct()[0].first * adjacentNumbers.distinct()[1].first)
            }
        }
        println(gearRatios.sum())
    }

    private fun getFullDigit(x: Int, y: Int): Pair<Int, IntRange> {
        var digit: String = splitInput[y][x].toString()
        var currentX = x
        var range = 0 .. 0
        while (true) {
            currentX -= 1
            if (currentX >= 0 && splitInput[y][currentX].isDigit()) digit = splitInput[y][currentX] + digit
            else {
                range = currentX + 1 .. range.last
                break
            }
        }
        currentX = x
        while (true) {
            currentX += 1
            if (currentX <= 139 && splitInput[y][currentX].isDigit()) digit += splitInput[y][currentX]
            else {
                range = range.first..<currentX
                break
            }
        }
        return Pair(digit.toInt(), range)
    }
}