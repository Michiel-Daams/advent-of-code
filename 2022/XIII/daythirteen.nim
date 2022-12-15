import strutils, json, algorithm

proc compare (left, right: JsonNode): int =
    for i in 0..<left.len():
        if i >= right.len(): # left has more entries than right
            return -1
        
        if left[i].kind == JInt and right[i].kind == JInt: # both are integers
            if left[i].getInt() < right[i].getInt():
                return 1
            elif left[i].getInt() > right[i].getInt():
                return -1
        
        if left[i].kind == JArray and right[i].kind == JArray: # both are lists
            let comparison = compare(left[i], right[i])
            if comparison != 0:
                return comparison

        if left[i].kind == JArray and right[i].kind == JInt: # left is list, right is integer, make right list and compare
            let comparison = compare(left[i], JsonNode(kind: JArray, elems: @[right[i]]))
            if comparison != 0:
                return comparison

        if left[i].kind == JInt and right[i].kind == JArray: # left is integer, right is list, make left list and compare
            let comparison = compare(JsonNode(kind: JArray, elems: @[left[i]]), right[i])
            if comparison != 0:
                return comparison

    if left.len() == right.len(): # all items compared and equal
        return 0
    else: # right has more entries than left
        return 1

let pairs = readFile("input.txt").split("\n\n")
var correctlyOrderedIndicesSum = 0
var orderedPackets = newSeq[JsonNode]()
for i in 0 .. pairs.high:
    let pair = pairs[i].split('\n')
    let left = pair[0].parseJson()
    let right = pair[1].parseJson()
    orderedPackets.add(left)
    orderedPackets.add(right)
    let comparison = compare(left, right)
    if comparison == 1: # part one
        correctlyOrderedIndicesSum += i + 1

# part two
orderedPackets.add(%[[2]])
orderedPackets.add(%[[6]])
orderedPackets.sort(proc (left, right: JsonNode): int =
    0 - compare(left, right))

echo correctlyOrderedIndicesSum
echo (orderedPackets.find(%[[2]]) + 1) * (orderedPackets.find(%[[6]]) + 1)
