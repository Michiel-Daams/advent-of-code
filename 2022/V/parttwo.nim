import strutils
import procs
import stack

let rows = readFile("input.txt").split({'\n'})
# echo rows

var crates = [Stack[char](), Stack[char](), Stack[char](), Stack[char](), Stack[char](), Stack[char](), Stack[char](), Stack[char](), Stack[char]()]

# read initial state
for row in 0 .. 7:
    for n in countup(1, 35, 4):
        let crate = rows[row][n]
        if crate != ' ':
            crates[n div 4].init(crate)

for row in 10 .. rows.high:
    let moveInstruction = parseMoveInstruction(rows[row])
    crates.executeMoveMultipleInstruction(moveInstruction)

for crate in crates:
     echo crate