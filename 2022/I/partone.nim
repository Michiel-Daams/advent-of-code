import strutils

let file = readFile("input.txt")
var spul = file.split({'\n'})
var max = 0
var current = 0
for ding in spul:
    if ding == "":
        current = 0
        continue
    current += parseInt(ding)
    if current > max:
        max = current
echo max