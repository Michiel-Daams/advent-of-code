package net.daams.solutions

import net.daams.Solution

class `2b`(input: String): Solution(input) {

    override fun run() {
        val powers: MutableList<Int> = emptyList<Int>().toMutableList()
        input.split("\n").map {
            var red: Int = 0
            var green: Int = 0
            var blue: Int = 0
            val rounds = it.split(": ")[1].split(";")
            rounds.forEach{
                it.split(", ").forEach{
                    val num = it.removePrefix(" ").split(" ")[0].toInt()
                    if (it.endsWith(" red").and(num > red)) red = num
                    else if (it.endsWith(" green").and(num > green)) green = num
                    else if (it.endsWith(" blue").and(num > blue)) blue = num
                }
            }
            powers.add(red * green * blue)
        }
        println(powers)
        println(powers.sum())
    }
}