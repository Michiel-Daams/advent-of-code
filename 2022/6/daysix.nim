import sets

let file = readFile("input.txt")

proc findDistinctSeries(seriesLength: int): int =
    for n in 0 .. file.high - seriesLength:
        if toHashSet(file[n .. n + seriesLength - 1]).len == seriesLength:
            return n + seriesLength

# part one
echo findDistinctSeries(4)

# part two
echo findDistinctSeries(14)