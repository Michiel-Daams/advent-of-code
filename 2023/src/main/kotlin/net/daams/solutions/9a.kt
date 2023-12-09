package net.daams.solutions

import net.daams.Solution

class `9a`(input: String): Solution(input) {

    override fun run() {
        val valueHistories = input.split("\n").map { history -> history.split(" ").map { it.toInt() }.toMutableList() }.toMutableList()
        val valuePredictions = valueHistories.map { mutableListOf(it) }.toMutableList()

        valuePredictions.forEach {prediction ->
            while (!(prediction.any{ steps -> steps.all{ it == 0 }})){
                prediction.add(getStepDistances(prediction.last()))
            }
        }

        var sumOfEvaluations = 0
        valuePredictions.forEach {
            sumOfEvaluations += extend(it)[0].last()
        }
        println(sumOfEvaluations)
    }

    fun extend (prediction: MutableList<MutableList<Int>>): List<List<Int>> {
        var prevStep = 0
        for (i in prediction.lastIndex downTo 0) {
            prevStep += prediction[i].last()
            prediction[i].add(prevStep)
        }
        return prediction
    }
}

fun getStepDistances(history: List<Int>): MutableList<Int> {
    val steps = mutableListOf<Int>()
    var prevStep = history[0]
    for (i in 1 .. history.lastIndex) {
        steps.add((history[i] - prevStep))
        prevStep = history[i]
    }
    return steps
}
