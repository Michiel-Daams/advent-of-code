import strutils
import sequtils
import algorithm

let input = readFile("input.txt").split("\n\n")

type
    Monkey = object
        items: seq[int]
        inspectionCount: int
        operation: string
        test, testTrue, testFalse: int

proc `$`(monkey: Monkey): string =
    return "Items: " & $monkey.items & 
            "\nInspection count: " & $monkey.inspectionCount & 
            "\nOperation: " & monkey.operation & 
            "\n Test: divisible by: " & $monkey.test & 
            "\n  True -> " & $monkey.testTrue & 
            "\n  False -> " & $monkey.testFalse

proc parseMonkey(monkey: string): Monkey =
    let steps = monkey.split('\n')
    let startingItems = steps[1].substr(18).split(", ").map do (x: string) -> int: parseInt(x)
    let operation = steps[2].substr(19)
    let test = parseInt(steps[3].substr(21))
    let testTrue = parseInt(steps[4].substr(29))
    let testFalse = parseInt(steps[5].substr(30))
    return Monkey(items: startingItems, operation: operation, test: test, testTrue: testTrue, testFalse: testFalse)

proc executeOperation(param1, param2: int, operator: string): int =
    if operator == "*":
        return param1 * param2
    elif operator == "+":
        return param1 + param2

proc executeTest(monkey: Monkey, worryLevel: int): int =
    if worryLevel mod monkey.test == 0:
        return monkey.testTrue
    else:
        return monkey.testFalse

# part one
var monkeys = newSeq[Monkey]()
for m in input:
    monkeys.add(parseMonkey(m))

for n in 1 .. 20:
    for m in 0 .. monkeys.high:
        for i in monkeys[m].items:
            let operation = monkeys[m].operation.replace("old", $i).split(' ')
            let newValue = executeOperation(parseInt(operation[0]), parseInt(operation[2]), operation[1]) div 3
            inc monkeys[m].inspectionCount
            let newMonkey = monkeys[m].executeTest(newValue)
            monkeys[newMonkey].items.add(newValue)
        monkeys[m].items = newSeq[int]()


for m in 0 .. monkeys.high:
    echo "Monkey " & $m
    echo monkeys[m]

var inspectionCounts = monkeys.map do (m: Monkey) -> int: m.inspectionCount
inspectionCounts.sort()
inspectionCounts.reverse()
echo inspectionCounts
echo inspectionCounts[0] * inspectionCounts[1]

# part two
monkeys = newSeq[Monkey]()
for m in input:
    monkeys.add(parseMonkey(m))

for n in 1 .. 10000:
    for m in 0 .. monkeys.high:
        for i in monkeys[m].items:
            let operation = monkeys[m].operation.replace("old", $i).split(' ')
            let newValue = executeOperation(parseInt(operation[0]), parseInt(operation[2]), operation[1]) mod 9699690
            inc monkeys[m].inspectionCount
            let newMonkey = monkeys[m].executeTest(newValue)
            monkeys[newMonkey].items.add(newValue)
        monkeys[m].items = newSeq[int]()


for m in 0 .. monkeys.high:
    echo "Monkey " & $m
    echo monkeys[m]

inspectionCounts = monkeys.map do (m: Monkey) -> int: m.inspectionCount
inspectionCounts.sort()
inspectionCounts.reverse()
echo inspectionCounts
echo inspectionCounts[0] * inspectionCounts[1]