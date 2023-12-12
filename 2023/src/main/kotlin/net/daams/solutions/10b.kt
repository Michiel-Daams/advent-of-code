package net.daams.solutions

import net.daams.Solution
import java.util.*

class `10b`(input: String): Solution(input) {

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