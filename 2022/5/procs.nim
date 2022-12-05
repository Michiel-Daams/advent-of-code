import strutils
import stack

proc parseMoveInstruction*(instruction: string): (int, int, int) = 
    let splitInstruction = instruction.split(' ')
    return (parseInt(splitInstruction[1]), parseInt(splitInstruction[3]) - 1, parseInt(splitInstruction[5]) - 1)
    
proc executeMoveInstruction*(crates: var array[0..8, Stack], instruction: (int, int, int)) =
    for n in 1 .. instruction[0]:
        let crateToMove = crates[instruction[1]].pop()
        crates[instruction[2]].push(crateToMove)

proc executeMoveMultipleInstruction*(crates: var array[0..8, Stack], instruction: (int, int, int)) =
    let cratesToMove = crates[instruction[1]].popMultiple(instruction[0])
    crates[instruction[2]].pushMultiple(cratesToMove)