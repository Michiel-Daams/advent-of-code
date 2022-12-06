import sets

let file = readFile("input.txt")
# part one
for n in 0 .. file.high - 4:
    if toHashSet(file[n .. n + 3]).len == 4:
        echo n + 4
        break

# part two
for n in 0 .. file.high - 14:
    if toHashSet(file[n .. n + 13]).len == 14:
        echo n + 14
        break