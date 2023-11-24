package net.daams

import java.lang.Exception

fun main(args: Array<String>) {
    val solution: Runnable
    try {
        val puzzleIndex = args[0].toInt()
        solution = Class.forName("net.daams.solutions." + args[0]).getDeclaredConstructor().newInstance() as Runnable
    } catch (e: Exception) {
        println("No valid puzzle index passed as parameter")
        return
    }
    println("Running solution for puzzle ${args.joinToString()}")
    solution.run()
}