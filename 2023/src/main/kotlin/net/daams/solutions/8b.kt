package net.daams.solutions

import net.daams.Solution

class `8b`(input: String): Solution(input) {

    override fun run() {
        val splitInput = input.split("\n")
        val directions = splitInput.first()
        val elements = splitInput.subList(2, splitInput.size).map { Element(it.split(" = ")[0]) }
        elements.forEachIndexed { index, element ->
            val connectedElements = splitInput[index + 2].split(" = ")[1].replace("""[()]""".toRegex(), "").split(", ")
            element.left = elements.find { it.value == connectedElements[0] }!!
            element.right = elements.find { it.value == connectedElements[1] }!!
        }
        val currentElements = elements.filter { it.value.endsWith("A") }
        val loopLengths = mutableListOf<Long>()
        currentElements.forEach {
            var loop = true
            var currentElement = it
            var stepCounter: Long = 0
            while (loop) {
                for (i in 0 .. directions.lastIndex) {
                    currentElement = if (directions[i] == 'L') currentElement.left!! else currentElement.right!!
                    stepCounter++
                    if (currentElement.value.endsWith("Z")) {
                        loop = false
                        break
                    }
                }
            }
            loopLengths.add(stepCounter)
        }
        var lowestLoop: Long = loopLengths[0]
        for (i in 1 .. loopLengths.lastIndex) {
            lowestLoop = lcm(lowestLoop, loopLengths[i])
        }

        println(lowestLoop)

    }

    private fun gcd(a: Long, b: Long): Long {
        return if (a == 0.toLong()) b else gcd(b % a, a)
    }

    private fun lcm(a: Long, b: Long): Long {
        return (a / gcd(a, b)) * b
    }
}