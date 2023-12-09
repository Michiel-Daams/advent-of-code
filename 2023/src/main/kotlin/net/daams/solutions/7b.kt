package net.daams.solutions

import net.daams.Solution

class `7b`(input: String): Solution(input) {

    override fun run() {
        val hands = mutableListOf<Pair<Hand, Int>>()
        input.split("\n").forEach {
            val hand = it.split(" ")
            hands.add(Pair(Hand(hand[0]), hand[1].toInt()))
        }
        val winnings = mutableListOf<Int>()
        hands.sortedBy { it.first }.forEachIndexed{ index, pair ->
            winnings.add(pair.second * (index + 1))
        }
        println(winnings.sum())
    }

    private class Hand(val hand: String): Comparable<Hand> {
        private val sortedHand = sortHand(hand)
        private var tier = 0

        init {
            var sameCards = 0
            sortedHand.forEach {
                if (it == sortedHand[0] || it == 'J') sameCards ++
            }
            this.tier = sameCards
            if (sameCards >= 3) this.tier++
            if (sameCards >= 4) this.tier++
            if (sameCards == 2 && sortedHand[2] == sortedHand[3]) this.tier = 3
            if (sameCards == 3 && sortedHand[3] == sortedHand[4]) this.tier = 5
        }

        override operator fun compareTo(other: Hand): Int {
            if (this.tier == other.tier) {
                for (i in 0 .. 4) {
                    if (Card(this.hand[i]).compareTo(Card(other.hand[i])) == 0) continue
                    return Card(this.hand[i]).compareTo(Card(other.hand[i]))
                }
            }
            return this.tier.compareTo(other.tier)
        }

        private fun sortHand(hand: String): String {
            val frequencyMap = hashMapOf<Char, Int>()
            hand.forEach {
                frequencyMap[it] = frequencyMap.getOrDefault(it, 0) + 1
            }

            var output = ""
            val nonJ = frequencyMap.toList().filter { it.first != 'J' }.sortedByDescending { it.second }
            val j = frequencyMap.getOrDefault('J', 0)
            if (nonJ.isNotEmpty()) {
                for (i in 1 .. nonJ[0].second) {
                    output += nonJ[0].first
                }
            }
            for (i in 1 .. j) {
                output += 'J'
            }
            if (nonJ.size > 1) {
                nonJ.subList(1, nonJ.size).forEachIndexed { index, pair ->
                    for (i in 1 .. nonJ[index + 1].second) {
                        output += pair.first
                    }

                }
            }

            return output
        }
    }


    private class Card(private val card: Char): Comparable<Card> {

        private val valueMap = hashMapOf(
            Pair('A', 13),
            Pair('K', 12),
            Pair('Q', 11),
            Pair('T', 10),
            Pair('9', 9),
            Pair('8', 8),
            Pair('7', 7),
            Pair('6', 6),
            Pair('5', 5),
            Pair('4', 4),
            Pair('3', 3),
            Pair('2', 2),
            Pair('J', 1))
        override operator fun compareTo(other: Card): Int {
            return valueMap[this.card]!!.compareTo(this.valueMap[other.card]!!)
        }
    }
}


