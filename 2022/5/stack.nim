type 
    Stack*[T] = object
        content*: seq[T]

proc top*[T](stack: Stack[T]): T = 
    return stack.content[stack.content.high]

proc topMultiple*[T](stack: Stack[T], amount: int): seq[T] = 
    return stack.content[stack.content.len - amount .. stack.content.high]

proc push*[T](stack: var Stack[T], item: T): void =
    stack.content.add(item)

proc pushMultiple*[T](stack: var Stack[T], item: seq[T]): void = 
    stack.content.add(item)

proc init*[T](stack: var Stack[T], item: T): void =
    stack.content.insert(item, 0)

proc pop*[T](stack: var Stack[T]): T =
    result = top(stack)
    if stack.content.high == 0:
        stack.content = @[]
    else: 
        stack.content.delete(stack.content.high)

proc popMultiple*[T](stack: var Stack[T], amount: int): seq[T] =
    result = stack.topMultiple(amount)
    if stack.content.len <= amount:
        stack.content = @[]
    else:
        stack.content = stack.content[0 .. stack.content.high - amount]