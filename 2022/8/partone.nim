import strutils

let treeLines = readFile("input.txt").split({'\n'})
var treeGrid: array[99, array[99, bool]]

# l to r
for n in 0 .. treeLines.high:
    var highest = -1
    for i in 0 .. treeLines[n].high:
        let tree = parseInt($treeLines[n][i])
        let visible = tree > highest
        if visible:
            treeGrid[n][i] = visible
            highest = tree
# r to l
for n in 0 .. treelines.high:
    var highest = -1
    for i in countdown(treeLines[n].high, 0, 1):
        let tree = parseInt($treeLines[n][i])
        let visible = tree > highest
        if visible:
            treeGrid[n][i] = visible
            highest = tree
# u to d
for n in 0 .. treelines.high:
    var highest = -1
    for i in 0.. treelines[n].high:
        let tree = parseInt($treeLines[i][n])
        let visible = tree > highest
        if visible:
            treeGrid[i][n] = visible
            highest = tree
# d to u
for n in 0 .. treelines[0].high:
    var highest = -1
    for i in countdown(treelines[n].high, 0, 1):
        let tree = parseInt($treeLines[i][n])
        let visible = tree > highest
        if visible:
            treeGrid[i][n] = visible
            highest = tree

proc `$`(boolean: bool): string =
    return if boolean: "T" else: "F"

proc `$`(boolArray: array[99, bool]): string =
    for b in boolArray:
        result &= $b

proc `$`(arrayArray: array[99, array[99, bool]]): string =
    for a in arrayArray:
        result &= $a & '\n'

echo treeGrid

var visibleTrees = 0
for n in 0 .. treegrid.high:
    for b in treeGrid[n]:
        if b:
            inc visibleTrees
echo visibleTrees
