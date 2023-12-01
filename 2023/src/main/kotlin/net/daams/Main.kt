package net.daams

import java.lang.Exception

fun main(args: Array<String>) {
    val solution: Solution
    try {
        val input = String(Solution::class.java.getResourceAsStream("/${args[0]}.txt",)?.readAllBytes()!!)
        solution = Class.forName("net.daams.solutions." + args[0]).getDeclaredConstructor(String::class.java).newInstance(input) as Solution
    } catch (e: Exception) {
        println("No valid puzzle index passed as parameter")
        return
    }
    println("Running solution for puzzle ${args.joinToString()}")
    solution.run()
}