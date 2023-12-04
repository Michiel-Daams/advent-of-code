package net.daams.solutions

import net.daams.Solution
import kotlin.math.pow

class `4a`(input: String): Solution(input) {

    var score: Int = 0

    override fun run() {
        val inputList = input.split("\n").map {
            it.split(": ")[1]
        }

        inputList.forEach {
            val winners = getAsIntList(it.split(" | ")[0])
            val myNumbers = getAsIntList(it.split(" | ")[1])
            println("winners: $winners, mine: $myNumbers")
            val myWinners = myNumbers.filter { winners.contains(it) }
            println(myWinners)
            score += (1 * 2.toDouble().pow(myWinners.size - 1)).toInt()
        }
        println(score)
    }

}

fun getAsIntList(input: String): List<Int> {
    val numbers = mutableListOf(input.substring(0 .. 1).replace(" ", "").toInt())
    if (input.length > 4) numbers.addAll(getAsIntList(input.substring(3..<input.length)))
    return numbers
}