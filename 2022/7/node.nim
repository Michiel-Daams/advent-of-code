import strutils

type 
    Node = ref object
        parent*: Node
        children*: seq[Node]
        name*: string
        value*: int
        dir*: bool

proc toString(node: Node, depth: int): string =
    var prefix = if node.children.len == 0: "|" else: "\\"
    prefix = prefix & node.name & " " & (if node.dir: "(dir)" else: "(file)")
    result = (prefix & " value: " & $node.value).indent(depth) & '\n'
    if node.children.len != 0:
        for child in node.children:
            result = result & child.toString(depth + 1)

proc `$`*(node: Node): string =
    return node.toString(0)


proc moveUp*(node: Node): Node = 
    return node.parent

proc moveDown*(node: Node, name: string): Node =
    for child in node.children:
        if child.dir and child.name == name:
            return child

proc addValue*(node: Node, value: int): void =
    node.value += value
    if node.parent != nil:
        node.parent.addValue(value)

proc createNode*(parent: Node, name: string, value: int, dir: bool): Node =
    return Node(parent: parent, children: @[], name: name, value: value, dir: dir)
    
proc getNodesWithValueUnder*(node: Node, value: int, result: var seq[int]): void =
    if node.value > 0 and node.value < value:
        result.add(node.value)
    for child in node.children:
        if child.dir:
            child.getNodesWithValueUnder(value, result)

proc getSmallestDirValueWithValueOver*(node: Node, value: int, smallest: var int): void =
    if node.dir and node.value > value and node.value < smallest:
        smallest = node.value
    for child in node.children:
        getSmallestDirValueWithValueOver(child, value, smallest)
