package net.daams.solutions

import net.daams.Solution

class `9b`(input: String): Solution(input) {

    override fun run() {
        val valueHistories = input.split("\n").map { history -> ArrayDeque(history.split(" ").map { it.toInt() }) }.toMutableList()
        val valuePredictions = valueHistories.map { mutableListOf(it) }.toMutableList()

        valuePredictions.forEach {prediction ->
            while (!(prediction.any{ steps -> steps.all{ it == 0 }})){
                prediction.add(ArrayDeque(getStepDistances(prediction.last())))
            }
        }

        var sumOfEvaluations = 0
        valuePredictions.forEach {
            sumOfEvaluations += extend(it)[0].first()
        }
        println(sumOfEvaluations)
    }

    private fun extend (prediction: MutableList<ArrayDeque<Int>>): List<List<Int>> {
        var prevStep = 0
        for (i in prediction.lastIndex downTo 0) {
            prediction[i].addFirst(prediction[i].first() - prevStep)
            prevStep = prediction[i].first()
        }
        return prediction
    }
}