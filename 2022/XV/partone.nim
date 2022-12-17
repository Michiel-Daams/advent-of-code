import strutils, sequtils

var sensors = newSeq[(int, int)]()
var sensorRanges = newSeq[int]()
var beacons = newSeq[(int, int)]()
# even indices are sensors, uneven are beacons
let input = readFile("input.txt").split('\n').map(proc(s: string): seq[string] = s.substr(12).split(": closest beacon is at x=")).concat()

proc getAbsoluteDistance(pos1, pos2: (int, int)): int =
    result += (if pos1[0] - pos2[0] > 0: pos1[0] - pos2[0] else: pos2[0] - pos1[0])
    result += (if pos1[1] - pos2[1] > 0: pos1[1] - pos2[1] else: pos2[1] - pos1[1])

for i in 0 .. input.high:
    let xy = input[i].split(", y=")
    let o = (parseInt(xy[0]), parseInt(xy[1]))
    if i mod 2 == 0: # even, sensor
        sensors.add(o)
    else: #uneven, beacon
        beacons.add(o)
        sensorRanges.add(o.getAbsoluteDistance(sensors[sensors.high]))

var noBeacon = newSeq[(int, int)]()
let y = 0
for s in 0 .. sensors.high:
    let distanceOfClosest = (sensors[s][0], y).getAbsoluteDistance(sensors[s])
    let diff = sensorRanges[s] - distanceOfClosest
    let amount = diff * 2 + 1
    let xIndices = (sensors[s][0] - amount div 2, sensors[s][0] + amount div 2)
    if xIndices[0] - xIndices[1] <= 0:
        noBeacon.add(xIndices)

proc mergeRanges(ranges: var seq[(int, int)]): void =
    var current = ranges[0]
    var currentIndex = 0
    var done = false
    while not done:
        done = true
        for r in 0 .. ranges.high:
            if ((current[0] >= ranges[r][0] and current[0] <= ranges[r][1]) or (current[1] >= ranges[r][0] and current[1] <= ranges[r][1])) and r != currentIndex:
                ranges.add((min(current[0], ranges[r][0]), max(current[1], ranges[r][1])))
                ranges.delete(r)
                ranges.delete(currentIndex)
                done = false # changed range, reset
                current = ranges[0]
                currentIndex = 0
                break
            elif r == ranges.high and currentIndex < ranges.high:
                inc currentIndex
                current = ranges[currentIndex]
                done = false
                break
            
var uniqueNoBeacons = noBeacon.deduplicate()
mergeRanges(uniqueNoBeacons)
var noBeaconCount = 0 - beacons.filter(proc(beacon: (int, int)): bool = beacon[1] == y).deduplicate().len
for r in uniqueNoBeacons:
    noBeaconCount += r[1] - r[0]
echo noBeaconCount + 1