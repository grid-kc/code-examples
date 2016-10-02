// Picks all the even numbers from 0 to limit and
// passes them as arguments to 'block' closure
def pickEven(limit, block) {
  0.step(limit+1, 2) {
    block it
  }
}

// Closures allow "delegation of implementation logic"
def acc = []
pickEven(4) { acc << it }
assert acc == [0,2,4]

def sum = 0
pickEven(6) { sum += it }
assert sum == 12

println "All assertions passed"
