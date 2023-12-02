package net.daams.solutions

import net.daams.Solution

class `2a`(input: String): Solution(input) {

    override fun run() {
        var gameIndex: Int
        val possibleGames: MutableList<Int> = emptyList<Int>().toMutableList()
        input.split("\n").map {
            gameIndex = it.split(":")[0].removePrefix("Game ").toInt()
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
            if (red <= 12 && green <= 13 && blue <= 14 ) possibleGames.add(gameIndex)
        }
        println(possibleGames)
        println(possibleGames.sum())
    }
}