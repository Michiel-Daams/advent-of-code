import strutils
import ../boolarray

#let instructions = ["R 5","U 8","L 8","D 3","R 17","D 10","L 25","U 20"]
#let instructions = ["R 4","U 4","L 3","D 1","R 4","D 1","L 5","R 2"]
let instructions = readFile("input.txt").split({'\n'})
let startPos = 500
var knots = @[(startPos, startPos), (startPos, startPos)]

var visited: array[1000, array[1000, bool]]
visited[startPos][startPos] = true

proc getDistance(pos1: (int, int), pos2: (int, int)): (int, int) =
    return (pos2[0] - pos1[0], pos2[1] - pos1[1])

proc stepCloser(toMove: (int, int), predecessor: (int, int)): (int, int) =
    let distance = getDistance(toMove, predecessor)
    result = toMove
    let xHigher = distance[0] > 1
    let xLower = distance[0] < -1
    let yHigher = distance[1] > 1
    let yLower = distance[1] < -1
    if xHigher:
        if yHigher:
            inc result[1]
        elif yLower:
            dec result[1]
        else:
            result[1] = predecessor[1]
        inc result[0]
    elif xLower:
        if yHigher:
            inc result[1]
        elif yLower:
            dec result[1]
        else:
            result[1] = predecessor[1]
        dec result[0]
    elif yHigher:
        if xHigher:
            inc result[1]
        elif xLower:
            dec result[1]
        else:
            result[0] = predecessor[0]
        inc result[1]
    elif yLower:
        if xHigher:
            inc result[1]
        elif xLower:
            dec result[1]
        else:
            result[0] = predecessor[0]
        dec result[1]

proc step(direction: string) = 
    case direction:
    of "L": dec knots[0][0]
    of "R": inc knots[0][0]
    of "U": inc knots[0][1]
    of "D": dec knots[0][1]
    for n in 1 .. knots.high:
        knots[n] = knots[n].stepCloser(knots[n - 1])
        #echo "Moving knot: " & $n & " to pos: " & $knots[n]
        if n == knots.high:
            visited[knots[n][0]][knots[n][1]] = true
    #echo ""

#part one
for instr in instructions:
    let splitInstr = instr.split({' '})
    for n in 1 .. parseInt(splitInstr[1]):
        step(splitInstr[0])

var counter = 0
for n in 0.. visited.high:
    for i in 0.. visited[n].high:
        if visited[n][i] == true:
            visited[n][i] = false
            inc counter
echo counter

# part two
knots = @[(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos),(startPos,startPos)]
visited[startPos][startPos] = true

for instr in instructions:
    let splitInstr = instr.split({' '})
    for n in 1 .. parseInt(splitInstr[1]):
        step(splitInstr[0])

counter = 0
for n in 0.. visited.high:
    for i in 0.. visited[n].high:
        if visited[n][i] == true:
            inc counter

echo counter