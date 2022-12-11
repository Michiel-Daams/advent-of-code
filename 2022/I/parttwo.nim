import strutils

let file = readFile("input.txt")
var spul = file.split({'\n'})
var 
    max1 = 0
    max2 = 0
    max3 = 0
var current = 0

proc replace_max3(max_candidate: int): void =
    if max_candidate > max3:
        max3 = max_candidate

proc replace_max2(max_candidate: int): void =
    if max_candidate > max2:
        replace_max3(max_2)
        max2 = max_candidate
    else:
        replace_max3(max_candidate)

proc replace_max1(max_candidate: int): void =
    if max_candidate > max1:
        replace_max2(max1)
        max1 = max_candidate
    else:
        replace_max2(max_candidate)


for ding in spul:
    if ding == "":
        replace_max1(current)
        current = 0
        continue
    current += parseInt(ding)

echo max1, "; ", max2, "; ", max3
echo max1+max2+max3