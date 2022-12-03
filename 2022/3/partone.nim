import strutils
import procs

let file = readFile("input.txt")
let rucksacks = file.split({'\n'})
#echo rucksacks


var commonItems: seq[(char, int)]
var totalPrio = 0
for rucksack in rucksacks:
    let totallength = rucksack.len
    let splitindex = int(totallength / 2)
    let compartmentOne = rucksack.substr(0, splitindex - 1)
    let compartmentTwo = rucksack.substr(splitindex, totallength)
    for character in compartmentOne:
        if compartmentTwo.contains(character):
            let commonCharacterPrio = getPrio(character)
            commonItems.add((character, commonCharacterPrio))
            totalPrio += commonCharacterPrio
            break


echo commonItems
echo totalPrio

