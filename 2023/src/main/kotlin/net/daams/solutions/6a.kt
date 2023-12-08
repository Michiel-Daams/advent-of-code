package net.daams.solutions

import net.daams.Solution

class `6a`(input: String): Solution(input) {

    override fun run() {
        val parsedInput = input.split("\n").map { it.replace("""\s+|Time:\s+|Distance:\s+""".toRegex(), " ").removePrefix(" ")}

        val timeAllowance = parsedInput[0].split(" ").map { it.toInt() }
        val records = parsedInput[1].split(" ").map { it.toInt() }

        val possibleDistances = mutableListOf<List<Int>>()

        timeAllowance.forEach { maxTime ->
            possibleDistances.add(getPossibleDistances(maxTime))
        }
        val differentWaysToWinPerRace = mutableListOf<Int>()
        records.forEachIndexed { index, i ->
            differentWaysToWinPerRace.add(0)
            possibleDistances[index].forEach { if (it > i) differentWaysToWinPerRace[index] += 1 }
        }
        var errorMargin = 0
        differentWaysToWinPerRace.forEach {
            if (errorMargin == 0) errorMargin = it
            else errorMargin *= it
        }
        println(errorMargin)
    }

    private fun getPossibleDistances(maxTime: Int): List<Int> {
        val output = mutableListOf<Int>()
        for (i in 0 .. maxTime) {
            output.add(i * (maxTime - i))
        }
        return output
    }
}