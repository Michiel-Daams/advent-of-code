import strutils
import procs

var file = readFile("input.txt")
let pairs = file.split({'\n'})
#echo pairs

var leftScore = 0
var rightScore = 0


proc lose(left: string): string =
    case left
    of "A":
        result = "C"
    of "B":
        result = "A"
    of "C":
        result = "B"

proc win(left: string): string =
    case left
    of "A":
        result = "B"
    of "B":
        result = "C"
    of "C":
        result = "A"


for pair in pairs:
    let divpair = pair.split(" ")
    var right: string
    case divpair[1]
    of "X":
        right = lose(divpair[0])
    of "Z":
        right = win(divpair[0])
    of "Y":
        right = divpair[0]

    leftScore += objectScore(divpair[0])
    rightScore += objectScore(right)
    let winner = calcWinner(divpair[0], right)
    leftScore += winner[0]
    rightScore += winner[1]

echo leftScore
echo rightScore