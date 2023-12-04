package net.daams.solutions

import net.daams.Solution
import kotlin.math.pow

class `4b`(input: String): Solution(input) {

    override fun run() {
        val inputList = input.split("\n").map {
            Pair(it.split(": ")[1], 1)
        }.toMutableList()
        inputList.forEachIndexed { index, it ->
            val winners = getAsIntList(it.first.split(" | ")[0])
            val myNumbers = getAsIntList(it.first.split(" | ")[1])
            val myWinners = myNumbers.filter { winners.contains(it) }
            for (i in index + 1 .. index + myWinners.size ) {
                inputList[i] = Pair(inputList[i].first, inputList[i].second + it.second)
            }
        }
        println(inputList.map{ it.second}.sum())
    }

}