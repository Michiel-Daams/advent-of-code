package net.daams.solutions

import net.daams.Solution

class `11b`(input: String): Solution(input) {

    val expandedUniverse = mutableListOf<String>()
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
        val galaxyLocations = mutableListOf<Pair<Int, Int>>()
        var yExpansion = ""
        for (i in 0 .. splitInput[0].lastIndex + xToExpand.size) {
            yExpansion += "@"
        }

        splitInput.forEachIndexed{ y, string ->
            if (yToExpand.contains(y)) expandedUniverse.add(yExpansion)
            var newRow = ""
            for (x in 0 .. splitInput[0].lastIndex) {
                if (xToExpand.contains(x)) newRow += "@"
                newRow += splitInput[y][x]
            }
            expandedUniverse.add(newRow)
        }
        expandedUniverse.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c == '#') galaxyLocations.add(Pair(y, x))
            }
        }
        var diff: Long = 0
        galaxyLocations.forEach{ pair ->
            galaxyLocations.forEach { pair2 ->
                diff += getDistance(pair, pair2)
            }
        }
        expandedUniverse.forEach { println(it) }
        println(diff / 2)
    }
    private fun getDistance(a: Pair<Int, Int>, b: Pair<Int, Int>): Long {
        var diff: Long = 0

        if (a.first > b.first) {
            diff += a.first - b.first
            for (i in b.first .. a.first){
                if (expandedUniverse[i][b.second] == '@') diff += 999998
            }
        } else {
            diff += b.first - a.first
            for (i in a.first .. b.first) {
                if (expandedUniverse[i][b.second] == '@') diff += 999998
            }
        }
        if (a.second > b.second) {
            diff += a.second - b.second
            for (i in b.second .. a.second) {
                if (expandedUniverse[b.first][i] == '@') diff += 999998
            }
        } else {
            diff += b.second - a.second
            for (i in a.second .. b.second) {
                if (expandedUniverse[b.first][i] == '@') diff += 999998
            }
        }

        return diff


    }
}