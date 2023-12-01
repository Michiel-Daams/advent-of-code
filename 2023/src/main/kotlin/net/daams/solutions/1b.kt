package net.daams.solutions

import net.daams.Solution

class `1b`(input: String): Solution(input) {

    val digits: Map<String, Int> = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

    override fun run() {
        val sumsOfFirstAndLastDigits = input.split("\n")
            .map { getNumbersInOrder(it) }
            .map { it.replace("""\D""".toRegex(), "") }.sumOf { "${it.first()}${it.last()}".toInt() }
        println(sumsOfFirstAndLastDigits)
    }

    private fun getNumbersInOrder(input: String): String {
        var output = ""
        if (input.isEmpty()) return output
        if (input[0].isDigit()) {
            output += input[0]
        } else {
            digits.keys.forEach { dk ->
                if (input.startsWith(dk)) {
                    output += digits[dk]
                }
            }
        }
        output += getNumbersInOrder(input.drop(1))
        return output

    }
}