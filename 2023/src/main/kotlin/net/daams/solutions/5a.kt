package net.daams.solutions

import net.daams.Solution

class `5a`(input: String): Solution(input) {

    override fun run() {
        val splitInput = input.split("\n\n")
        val seeds = splitInput[0].removePrefix("seeds: ").split(" ").map{ it.toLong() }
        val maps = mutableListOf<List<Pair<LongRange, LongRange>>>()
            splitInput.subList(1, splitInput.size).forEach {
            val destinationRanges = mutableListOf<Pair<LongRange, LongRange>>()
            val rangeText = it.split("\n")
            rangeText.subList(1, rangeText.size).forEach {
                val meuk = it.split(" ").map { it.toLong() }
                val destinationRange = meuk[0]..meuk[0] + meuk[2] - 1
                val sourceRange = meuk[1] .. meuk[1] + meuk[2] - 1
                destinationRanges.add(Pair(sourceRange, destinationRange))
            }
            maps.add(destinationRanges)
        }
        val destinations = mutableListOf<Long>()
        seeds.forEach {seed ->
            var prevDestination = seed
            maps.forEach { map ->
                prevDestination = getDestination(map, prevDestination)
            }
            destinations.add(prevDestination)
        }
        println(destinations.min())
    }

    fun getDestination(ranges: List<Pair<LongRange, LongRange>>, source: Long): Long {
        ranges.forEach {
            if (source in it.first) return source - it.first.first + it.second.first
        }
        return source
    }
}