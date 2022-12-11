import strutils

let file = readFile("input.txt")
let pairs = file.split({'\n'})
#echo pairs

proc parseRange(valueRange: string): (int, int) =
    let strings = valueRange.split({'-'})
    return (parseInt(strings[0]), parseInt(strings[1]))

var rangeFullyContainedCount = 0
var rangePartiallyContainedCount = 0

for pair in pairs:
    let pairArray = pair.split({','})
    let left = pairArray[0].parseRange()
    let right = pairArray[1].parseRange()
    
    # part one
    if (left[0] >= right[0] and left[1] <= right[1]) or (right[0] >= left[0] and right[1] <= left[1]):
        inc rangeFullyContainedCount

    # part two
    for n in left[0] .. left[1]:
        if n >= right[0] and n <= right[1]:
            inc rangePartiallyContainedCount
            break


echo rangeFullyContainedCount
echo rangePartiallyContainedCount