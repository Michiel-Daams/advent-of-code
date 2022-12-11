import strutils

let treeLines = readFile("input.txt").split({'\n'})

proc getScenicScore(row: int, col: int): int = 
    var l, r, u, d: int
    let treehouseHeight = parseInt($treeLines[col][row])
    # l
    for n in countdown(row - 1, 0):
        inc l
        if parseInt($treeLines[col][n]) >= treehouseHeight: 
            break
    # r
    for n in row + 1 .. treelines.high:
        inc r
        if parseInt($treeLines[col][n]) >= treehouseHeight:
            break 
    # u
    for n in countdown(col - 1, 0):
        inc u
        if parseInt($treeLines[n][row]) >= treehouseHeight:
            break
    # d
    for n in col + 1 .. treelines.high:
        inc d
        if parseInt($treeLines[n][row]) >= treehouseHeight:
            break
    return l * r * u * d

var highScore = 0
for col in 0 .. treelines.high:
    for row in 0 .. treelines.high:
        let score = getScenicScore(row, col)
        highScore = if score > highScore: score else: highScore

echo highScore