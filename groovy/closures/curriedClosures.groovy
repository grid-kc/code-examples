def reduce = { acc, list, block ->
  list.forEach {
    acc = block(acc, it)
  }
  acc
}

def baseReduce = reduce.curry(0)
def baseBlock = { a, b -> a + b }
assert 6 == baseReduce([1,2,3], baseBlock)

assert "allurbase" == reduce("",["all","ur","base"],baseBlock)

def sumIntList = baseReduce.rcurry(baseBlock)
assert sumIntList([4,5,6]) == 15

println "All assertions passed"
