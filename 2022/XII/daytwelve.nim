import strutils
import sequtils
import ../boolarray

let input = readFile("inputparttwo.txt").split('\n')

var parsedInput = newSeq[seq[int]]()
var visited = newSeq[seq[bool]]()
var currentPos = newSeq[(int, int)]()
var endPos: (int, int)

proc getEligibleNeighbours(currentCoords: (int, int)): seq[(int, int)] =
    result = newSeq[(int, int)]()
    if currentCoords[1] - 1 >= 0: # l
        result.add((currentCoords[0], currentCoords[1] - 1))
    if currentCoords[1] + 1 < parsedInput[currentCoords[0]].len: # r
        result.add((currentCoords[0], currentCoords[1] + 1))
    if currentCoords[0] - 1 >= 0: # u
        result.add((currentCoords[0] - 1, currentCoords[1]))
    if currentCoords[0] + 1 < parsedInput.len: # d
        result.add((currentCoords[0] + 1, currentCoords[1]))
    result = result.filter do (coord: (int, int)) -> bool: not visited[coord[0]][coord[1]]
    let currentElevation = parsedInput[currentCoords[0]][currentCoords[1]]
    result = result.filter do (coord: (int, int)) -> bool: parsedInput[coord[0]][coord[1]] in (0 .. currentElevation + 1)

for r in 0 .. input.high:
    parsedInput.add(newSeq[int]())
    visited.add(newSeq[bool]())
    for n in 0 .. input[r].high:
        if input[r][n].isLowerAscii():
            parsedInput[r].add(int(input[r][n]) - 96)
            visited[r].add(false)
        elif input[r][n] == 'S':
            parsedInput[r].add(2)
            visited[r].add(true)
            currentPos.add((r,n))
        elif input[r][n] == 'E':
            endPos = (r, n)
            parsedInput[r].add(27)
            visited[r].add(false)

var stepCounter = 1

while true:
    var newPositions = newSeq[(int, int)]()
    for c in currentPos:
        let eligibleNeighbours = getEligibleNeighbours(c)
        for e in eligibleNeighbours:
            visited[e[0]][e[1]] = true
        newPositions.add(eligibleNeighbours)
    if newPositions.contains(endPos):
        break
    currentPos = newPositions
    inc stepCounter


for r in 0 .. parsedInput.high:
    var rowString = ""
    for n in 0 .. parsedInput[r].high:
        rowString &= $parsedInput[r][n]
    echo rowString
echo visited.toString()
echo stepCounter # answer to part two is stepCounter + 1
