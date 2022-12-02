proc calcWinner*(left: string, right: string): array[2, int] =
    if left == right:
        result = [3,3]
    elif (left == "A" and right == "B") or (left == "B" and right == "C") or (left == "C" and right == "A"):
        result = [0,6]
    elif (right == "A" and left == "B") or (right == "B" and left == "C") or (right == "C" and left == "A"):
        result = [6,0]

proc objectScore*(obj: string): int =
    if obj == "A":
        result = 1
    elif obj == "B":
        result = 2
    elif obj == "C":
        result = 3