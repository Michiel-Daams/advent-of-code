import strutils

proc `$`(cave: seq[string]): string =
    for row in cave:
        result &= (row & '\n')

let input = readFile("input.txt").split('\n')

var coordinates = newSeq[seq[(int, int)]]()
var xLowHigh = (100000000, 0)
var yHigh = 0
# parse input and record dimensions
for i in 0 .. input.high:
    let cs = input[i].split(" -> ")
    coordinates.add(newSeq[(int, int)]())
    for c in cs:
        let split = c.split(',')
        let parsedCoord = (parseInt(split[0]), parseInt(split[1]))
        if parsedCoord[0] < xLowHigh[0]: xLowHigh[0] = parsedCoord[0]
        if parsedCoord[0] > xLowHigh[1]: xLowHigh[1] = parsedCoord[0]
        if parsedCoord[1] > yHigh: yHigh = parsedCoord[1] + 2
        coordinates[i].add((parsedCoord))

var cave = newSeq[string]()
for y in 0 .. yHigh: # build grid, no walls yet
    cave.add("")
    for x in 0 .. xLowHigh[1] - xLowHigh[0]:
        cave[y] &= "."

# add  floor
for n in 0 .. cave[yHigh].high:
    cave[yHigh][n] = '#'

for wall in coordinates: # put in walls
    var prev = (-1, -1)
    for line in wall:
        if prev == (-1, -1): # first iteration
            prev = line
        else: 
            let prevX = prev[0] - xLowHigh[0]
            let lineX = line[0] - xLowHigh[0]
            if line[0] > prev[0]: # draw from prev[0] to line[0]
                for n in prevX .. lineX:
                    cave[line[1]][n] = '#'
            elif line[0] < prev[0]: # draw from line[0] to prev[0]
                for n in lineX .. prevX:
                    cave[line[1]][n] = '#'
            elif line[1] > prev[1]: # draw from prev[1] to line[1]
                for n in prev[1] .. line[1]:
                    cave[n][lineX] = '#'
            elif line[1] < prev[1]: # draw from line[1] to prev[1]
                for n in line[1] .. prev[1]:
                    cave[n][lineX] = '#'
            prev = line

let sandOrigin = 500 - xLowHigh[0]
var xOffset = 0
var grainSettledAtOrigin = false
var grainCount = 0
while not grainSettledAtOrigin: # drop in sand from 500, 0
    var grainPos = (x: sandOrigin + xOffset, y:0)
    while true:
        if cave[grainPos.y + 1][grainPos.x] == '.': # if sand can move down straight by one, do this
            grainPos = (grainPos.x, grainPos.y + 1)
            continue
        if grainPos.x - 1 < 0: # sand moves out of bounds, expand to left
            for row in 0 .. cave.high:
                if row == yHigh:
                    cave[row] = '#' & cave[row]
                else:
                    cave[row] = '.' & cave[row]
            inc xOffset
            inc grainPos.x 
        if cave[grainPos.y + 1][grainPos.x - 1] == '.': # if sand can move down + left by one, do this
            grainPos = (grainPos.x - 1, grainPos.y + 1)
            continue
        if grainPos.x + 1 > cave[grainPos.y + 1].high: # sand moves out of bounds, expand to right
            for row in 0 .. cave.high:
                if row == yHigh:
                    cave[row] &= '#'
                else:
                    cave[row] &= '.'
        if cave[grainPos.y + 1][grainPos.x + 1] == '.': # if sand can move down + right by one, do this
            grainPos = (grainPos.x + 1, grainPos.y + 1)
            continue
        else:
            break
    # check if grain has settled at origin
    if grainPos == (sandOrigin + xOffset, 0):
        grainSettledAtOrigin = true
    inc grainCount
    cave[grainPos.y][grainPos.x] = 'O'


echo xLowHigh
echo yHigh
echo coordinates
echo $cave
echo grainCount

# create grid