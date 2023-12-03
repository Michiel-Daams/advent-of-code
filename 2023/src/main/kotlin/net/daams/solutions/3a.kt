package net.daams.solutions

import net.daams.Solution

class `3a`(input: String): Solution(input) {

    private val allNumbers: MutableList<List<Pair<String, Int>>> = mutableListOf()
    private val allSymbols: MutableList<List<Int>> = mutableListOf()
    private val adjacentNumbers: MutableList<Int> = mutableListOf()
    private var currentNumber = ""
    private var currentIndex = -1
    override fun run() {
        val splitInput = input.split("\n")
        // find numbers + starting index per number
        splitInput.forEach{
            val numbers : MutableList<Pair<String, Int>> = mutableListOf()
            it.forEachIndexed { index, s ->
                if (!s.isDigit() && currentNumber != "") {
                    numbers.add(Pair(currentNumber, currentIndex))
                }

                if (currentNumber.equals("")) currentIndex = index
                if (s.isDigit()) currentNumber += s else currentNumber = ""
                if (index + 1 == it.length && currentNumber != "") numbers.add(Pair(currentNumber, currentIndex))
            }
            currentNumber = ""
            currentIndex = -1
            allNumbers.add(numbers.toList())
        }
        splitInput.forEach{
            val indices: MutableList<Int> = mutableListOf()
            it.forEachIndexed{ index, s ->
                if (!s.isDigit() && s != '.') indices.add(index)
            }
            allSymbols.add(indices.toList())
        }

        allNumbers.forEachIndexed{ indexY, pairList ->
            pairList.forEach {
                if (isInRange(it, indexY)) adjacentNumbers.add(it.first.toInt())
            }
        }

        println(adjacentNumbers.sum())
    }

    fun isInRange(xs: Pair<String, Int>, y: Int): Boolean {
        for(x: Int in xs.second - 1 .. xs.second + xs.first.length) {
            if (x < 0 || x > 139) continue
            for (yy: Int in y - 1 .. y + 1) {
                if (yy < 0 || yy > 139) continue
                if (allSymbols[yy].contains(x)) return true
            }
        }
        return false
    }
}