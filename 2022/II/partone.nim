import strutils
import procs

var file = readFile("input.txt")
file = file.replace("X", "A")
file = file.replace("Y", "B")
file = file.replace("Z", "C")
let pairs = file.split({'\n'})
#echo pairs

var leftScore = 0
var rightScore = 0

for pair in pairs:
    let divpair = pair.split(" ")
    leftScore += objectScore(divpair[0])
    rightScore += objectScore(divpair[1])
    let winner = calcWinner(divpair[0], divpair[1])
    leftScore += winner[0]
    rightScore += winner[1]

echo leftScore
echo rightScore