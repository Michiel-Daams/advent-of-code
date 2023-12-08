package net.daams.solutions

import net.daams.Solution

class `6b`(input: String): Solution(input) {

    override fun run() {
        val parsedInput = input.split("\n").map { it.replace("""\s+|Time:\s+|Distance:\s+""".toRegex(), "").toLong()}

        println(parsedInput)
        var winningRange = LongRange(0, 0)
        for (i in 0 .. parsedInput[0]) {
            if ((i * (parsedInput[0] - i)) > parsedInput[1]) {
                winningRange = i .. 0
                break
            }
        }
        for (i in parsedInput[0] downTo 0) {
            if ((i * (parsedInput[0] - i)) > parsedInput[1]) {
                winningRange = winningRange.first .. i
                break
            }
        }
        println(winningRange)
        println(winningRange.last - winningRange.first + 1)
    }

}