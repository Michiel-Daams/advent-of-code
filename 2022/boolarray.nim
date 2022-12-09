proc toString(boolean: bool): string =
    return if boolean: "T" else: "F"

proc toString[I](boolArray: array[I, bool]): string =
    for b in boolArray:
        result &= b.toString()

proc toString*[I](arrayArray: array[I, array[I, bool]]): string =
    for a in arrayArray:
        result &= a.toString() & '\n'
