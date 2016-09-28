// Upto loops
def u = []
0.upto(2) { u << it }
assert [0,1,2] == u

// Times loops
def t = []
3.times { t << it }
assert [0,1,2] == t

// Step loops
def s = []
0.step(5, 2) { s << it }
assert [0,2,4] == s

println("All assertions passed")
