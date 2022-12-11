import strutils
import procs

let file = readFile("input.txt")
# len of rucksacks is 300
let rucksacks = file.split({'\n'})

var groups: seq[(string, string, string)]

# sort into groups
for n in countup(0, rucksacks.len - 1, 3):
    groups.add((rucksacks[n], rucksacks[n+1], rucksacks[n+2]))


var commonItems: seq[int]
var commonItemSum = 0
for group in groups:
    for item in group[0]:
        if (group[1].contains(item) and group[2].contains(item)):
            let prio = getPrio(item)
            commonItems.add(prio)
            commonItemSum += prio
            break

#echo groups
#echo commonItems
echo commonItemSum