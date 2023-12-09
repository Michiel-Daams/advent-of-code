package net.daams.solutions

import net.daams.Solution

class `8a`(input: String): Solution(input) {

    override fun run() {
        val splitInput = input.split("\n")
        val directions = splitInput.first()
        val elements = splitInput.subList(2, splitInput.size).map { Element(it.split(" = ")[0]) }
        elements.forEachIndexed { index, element ->
            val connectedElements = splitInput[index + 2].split(" = ")[1].replace("""[()]""".toRegex(), "").split(", ")
            element.left = elements.find { it.value == connectedElements[0] }!!
            element.right = elements.find { it.value == connectedElements[1] }!!
        }

        var currentElement = elements.find { it.value == "AAA" }!!
        var stepCounter = 0
        var loop = true
         while (loop) {
            for (i in 0 .. directions.lastIndex) {
                currentElement = if (directions[i] == 'L') currentElement.left!! else currentElement.right!!
                stepCounter++
                if (currentElement.value == "ZZZ") {
                    loop = false
                    break
                }
            }
        }
        println(stepCounter)
    }
}

class Element(val value: String) {

    var left: Element? = null
    var right: Element? = null

    override fun toString(): String {
        return "$value = (${left?.value}, ${right?.value})"
    }

}