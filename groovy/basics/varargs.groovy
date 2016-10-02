def varArgs(int a, int... b) {
  b
}

def arrArgs(int a, int[] b) {
  b
}

def a1 = varArgs(1,2,3,4)
def a2 = arrArgs(1,2,3,4)

assert a1 == [2,3,4]
assert a1 == a2

println("All assertions passed")
