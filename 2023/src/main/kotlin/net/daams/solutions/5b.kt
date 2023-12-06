package net.daams.solutions

import net.daams.Solution

class `5b`(input: String): Solution(input) {

    override fun run() {
        val splitInput = input.split("\n\n")
        val seeds = splitInput[0].removePrefix("seeds: ").split(" ").map{ it.toLong() }
        val seedRanges = mutableListOf<LongRange>()
        for (i in 0 .. seeds.lastIndex step 2) {
            seedRanges.add(seeds[i]..<seeds[i] + seeds[i + 1])
        }

        val maps = mutableListOf<List<Pair<LongRange, LongRange>>>()
        splitInput.subList(1, splitInput.size).forEach {
            val destinationRanges = mutableListOf<Pair<LongRange, LongRange>>()
            val rangeText = it.split("\n")
            rangeText.subList(1, rangeText.size).forEach {
                val meuk = it.split(" ").map { it.toLong() }
                val destinationRange = meuk[0]..< meuk[0] + meuk[2]
                val sourceRange = meuk[1]..< meuk[1] + meuk[2]
                destinationRanges.add(Pair(sourceRange, destinationRange))
            }
            maps.add(destinationRanges)
        }

        val destinations = mutableListOf<LongRange>()
        seedRanges.forEach {seedRange ->
            var prevDestinations = mutableListOf(seedRange)
            maps.forEach { map ->
                val evaluatedDestinations = mutableListOf<LongRange>()
                prevDestinations.forEach { destination ->
                    val moddedDestinations = getDestinationRanges(map, destination).toMutableList()
                    if (moddedDestinations.isEmpty()) evaluatedDestinations.add(destination) else evaluatedDestinations.addAll(moddedDestinations)
                }
                prevDestinations = evaluatedDestinations
            }
            destinations.addAll(prevDestinations.toList())
        }
        println(destinations.map { it.first }.min())
    }

    fun getDestinationRanges(rangesToTarget: List<Pair<LongRange, LongRange>>, sourceRange: LongRange): List<LongRange> {
        val destinationRanges = mutableListOf<LongRange>()
        val remainingSourceRange = mutableListOf(sourceRange)
        rangesToTarget.forEach { rangeToTarget ->
            val remainingRanges = mutableListOf<LongRange>()
            val toRemove = mutableListOf<Int>()
            remainingSourceRange.forEachIndexed{ index, sourceRange ->
                val splitRanges = getCollidingRange(sourceRange, rangeToTarget.first)
                if (!splitRanges.isEmpty()) toRemove.add(index)
                splitRanges.getOrNull(0)?.let { destinationRanges.add((it.first - rangeToTarget.first.first + rangeToTarget.second.first) .. (it.last - rangeToTarget.first.first + rangeToTarget.second.first)) }
                splitRanges.getOrNull(1)?.let { remainingRanges.add(it)}
                splitRanges.getOrNull(2)?.let { remainingRanges.add(it)}
            }
            remainingSourceRange.addAll(remainingRanges)
            toRemove.forEach { remainingSourceRange.removeAt(it) }
        }
        destinationRanges.addAll(remainingSourceRange)
        return destinationRanges
    }

    // returns 0 ranges if no overlap
    // returns 1 range if source range is entirely contained in target
    // returns 2 ranges if source range partially overlaps target
    // returns 3 ranges if source range is bigger than target
    fun getCollidingRange(source: LongRange, target: LongRange): List<LongRange?> {
        val output = mutableListOf<LongRange>()
        if (source.first < target.first && source.last >= target.first && source.last <= target.last) { // left partial overlap
            output.add(target.first .. source.last)
            output.add(source.first..< target.first)
            return output
        }
        if (source.last > target.last && source.first <= target.last && source.first >= target.first) { // right partial overlap
            output.add(source.first .. target.last)
            output.add(target.last + 1 .. source.last)
            return output
        }
        if (source.first >= target.first && source.last <= target.last) { // entirely contained
            output.add(source)
            return output
        }
        if (source.first < target.first && source.last > target.last) { // source is bigger than target
            output.add(target)
            output.add(source.first..<target.first)
            output.add(target.last + 1 .. source.last)
            return output
        }
        return output
    }
}