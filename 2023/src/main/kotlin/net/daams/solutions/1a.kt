package net.daams.solutions

import net.daams.Solution

class `1a`(input: String) : Solution(input) {
    override fun run() {
        val sumsOfFirstAndLastDigits: Int = input.split("\n").map {
            it.replace("""\D""".toRegex(), "")
        }.map { "${it.first()}${it.last()}".toInt() }.sum()
        println(sumsOfFirstAndLastDigits)
    }
}