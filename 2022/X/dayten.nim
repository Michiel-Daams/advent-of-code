import strutils

let operations = readFile("input.txt").split({'\n'})

var cycles = 1
var x = 1
var done = false
var opIndex = 0
var opCyclesRemaining = 1
var signalStrengthSum = 0
var crt = ["#", "", "", "", "", "", ""]

while not done:
    if opCyclesRemaining == 0: 
        if operations[opIndex] == "noop":
            opCyclesRemaining = 1
        else:
            x += parseInt(operations[opIndex].split({' '})[1])
            opCyclesRemaining = 2
        inc opIndex

    # part two
    let crtColumn = cycles mod 40
    let crtRow = cycles div 40
    if x in (crtColumn - 1 .. crtColumn + 1):
        crt[crtRow].add("#")
    else:
        crt[crtRow].add(".")
    # --

    inc cycles
    dec opCyclesRemaining
    
    # part one
    if ((cycles - 20) mod 40) == 0:
        signalStrengthSum += cycles * x
    # --

    if opIndex == operations.high:
        done = true

echo signalStrengthSum

for n in 0 .. 5:
    echo crt[n]
