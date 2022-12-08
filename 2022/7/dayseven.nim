import strutils
import node

let instructions = readFile("input.txt").split({'\n'})

var root = createNode(nil, "root", 0, true)
var currentNode = root

proc findNextDirChange(startIndex: int): int = 
    for n in startIndex .. instructions.high:
        if instructions[n].startsWith("$"):
            return n
    return instructions.len

for n in 0 .. instructions.high:
    if instructions[n] == "$ cd ..":
        currentNode = currentNode.moveUp()
    elif instructions[n] == "$ cd /":
        currentNode = root
    elif instructions[n].startsWith("$ cd"):
        currentnode = currentNode.moveDown(instructions[n].split({' '})[2])
    elif instructions[n] == "$ ls":
        let nextDirChange = findNextDirChange(n + 1)
        for i in n + 1 .. nextDirChange - 1:
            echo instructions[i]
            let splitInstruction = instructions[i].split({' '})
            if splitInstruction[0] == "dir":
                currentNode.children.add(currentNode.createNode(splitInstruction[1], 0, true))
            else: 
                currentNode.children.add(currentNode.createNode(splitInstruction[1], parseInt(splitInstruction[0]), false))
                currentNode.addValue(parseInt(splitInstruction[0]))

echo $root

var dirs = newSeq[int]()
root.getNodesWithValueUnder(100000, dirs)
echo dirs
var count = 0
for dir in dirs:
    count += dir

echo count
let spaceLeft = 70000000 - root.value 
let spaceNeeded = 30000000 - spaceLeft

var smallest = int.high
root.getSmallestDirValueWithValueOver(spaceNeeded, smallest)
echo smallest