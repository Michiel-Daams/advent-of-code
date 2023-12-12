package net.daams.solutions

import net.daams.Solution

class `11a`(input: String): Solution(input) {

    override fun run() {
        val splitInput = input.split("\n")

        val xToExpand = mutableListOf<Int>()
        val yToExpand = mutableListOf<Int>()

        splitInput.forEachIndexed { index, s ->
            if (s.all { it == '.' }) {
                yToExpand.add(index)
            }
        }
        for (x in 0 .. splitInput[0].lastIndex) {
            var expand = true
            splitInput.forEachIndexed { y, s ->
                if (splitInput[y][x] != '.') expand = false
            }
            if (expand) xToExpand.add(x)
        }
        val expandedUniverse = mutableListOf<String>()
        val galaxyLocations = mutableListOf<Pair<Int, Int>>()
        var yExpansion = ""
        for (i in 0 .. splitInput[0].lastIndex + xToExpand.size) {
            yExpansion += "."
        }

        splitInput.forEachIndexed{ y, string ->
            if (yToExpand.contains(y)) expandedUniverse.add(yExpansion)
            var newRow = ""
            for (x in 0 .. splitInput[0].lastIndex) {
                if (xToExpand.contains(x)) newRow += "."
                newRow += splitInput[y][x]
            }
            expandedUniverse.add(newRow)
        }
        expandedUniverse.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c == '#') galaxyLocations.add(Pair(y, x))
            }
        }
        var diff = 0
        galaxyLocations.forEach{ pair ->
            galaxyLocations.forEach { pair2 ->
                diff += getDistance(pair, pair2)
            }
        }
        expandedUniverse.forEach { println(it) }
        println(diff / 2)
    }

    fun getDistance(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
        var diff = 0
        diff += if (a.first > b.first) a.first - b.first
        else b.first - a.first
        diff += if (a.second > b.second) a.second - b.second
        else b.second - a.second
        return diff
    }
}

