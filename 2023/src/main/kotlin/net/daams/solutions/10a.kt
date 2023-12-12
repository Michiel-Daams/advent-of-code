package net.daams.solutions

import net.daams.Solution
import java.util.Stack

class `10a`(input: String): Solution(input) {

    private val pipes = mutableListOf<MutableList<Pipe?>>()
    private lateinit var startingLocation: Pair<Int, Int>
    override fun run() {
        input.split("\n").forEachIndexed { y, pipeRow ->
            val p = mutableListOf<Pipe?>()

            pipeRow.forEachIndexed { x, c ->
                if (c != '.') {
                    val pipeToAdd = Pipe(c, Pair(y, x))
                    if (c == 'S') startingLocation = Pair(y, x)
                    p.add(pipeToAdd)
                } else p.add(null)
            }
            pipes.add(p)
        }
        var loopFound = false
        val types = Stack<Char>()
        types.push('7')
        types.push('J')
        types.push('F')
        types.push('L')
        types.push('-')
        types.push('|')
        var loopLength = 0
        while (!loopFound) {
            pipes[startingLocation.first][startingLocation.second]!!.pipe = types.pop()
            pipes.forEach { pipez -> pipez.forEach {
                it?.makeConnections(pipes)
            } }
            loopLength = 0
            var prev: Pipe? = null
            var current: Pipe = pipes[startingLocation.first][startingLocation.second]!!
            while (true) {
                loopLength++
                val next = getNext(prev, current) ?: break
                if (next.location == startingLocation) {
                    loopFound = true
                    break
                }
                prev = current
                current = next
            }

        }
        println(loopLength)
        println(loopLength / 2)
        println(pipes[startingLocation.first][startingLocation.second])
    }
}

fun getNext(prev: Pipe?, current: Pipe): Pipe? {
    if (prev == null) {
        if (current.connection2 != null && (current.connection2!!.connection1 == current || current.connection2!!.connection2 == current)) return current.connection2
        if (current.connection1 != null && (current.connection1!!.connection1 == current || current.connection1!!.connection2 == current)) return current.connection1
        return null
    }
    return if (current.connection1 == prev){
        if (current.connection2 != null && (current.connection2!!.connection1 == current || current.connection2!!.connection2 == current)) {
            current.connection2
        } else null
    } else {
        if (current.connection1 != null && (current.connection1!!.connection1 == current || current.connection1!!.connection2 == current)) {
            current.connection1
        } else null
    }
}

class Pipe(var pipe: Char, val location: Pair<Int, Int>) {
    var connection1: Pipe? = null
    var connection2: Pipe? = null

    fun makeConnections(gridToCheck: List<List<Pipe?>>) {
        connection1 = null
        connection2 = null
        var connectionToMake: Pair<Pair<Int, Int>, Pair<Int, Int>> = Pair(Pair(-1, -1), Pair(-1, -1))
        if (pipe == 'F') connectionToMake = Pair(Pair(location.first + 1, location.second), Pair(location.first, location.second + 1))
        if (pipe == 'J') connectionToMake = Pair(Pair(location.first -1, location.second), Pair(location.first, location.second - 1))
        if (pipe == '7') connectionToMake = Pair(Pair(location.first, location.second - 1), Pair(location.first + 1, location.second))
        if (pipe == 'L') connectionToMake = Pair(Pair(location.first, location.second + 1), Pair(location.first - 1, location.second))
        if (pipe == '|') connectionToMake = Pair(Pair(location.first - 1, location.second), Pair(location.first + 1, location.second))
        if (pipe == '-') connectionToMake = Pair(Pair(location.first, location.second + 1), Pair(location.first, location.second - 1))
        try {
            connection1 = gridToCheck[connectionToMake.first.first][connectionToMake.first.second]
        } catch (_: IndexOutOfBoundsException) {}
        try {
            connection2 = gridToCheck[connectionToMake.second.first][connectionToMake.second.second]
        } catch (_: IndexOutOfBoundsException) {}
    }

    override fun equals(other: Any?): Boolean {
        return other is Pipe && other.location == this.location
    }

    override fun toString(): String {
        return pipe.toString()
    }
}

fun MutableList<Pipe?>.toGrid(): String {
    var s = ""
    this.forEach { s += it?.pipe ?: '.' }
    return s
}