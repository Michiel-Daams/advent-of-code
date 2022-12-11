import strutils

proc getPrio*(item: char): int =
    if isLowerAscii(item):
        result = int(item) - 96
    else:
        result = int(item) - 38