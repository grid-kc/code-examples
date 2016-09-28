//Normal stuffs
def intList = [1,2,3]
assert "java.util.ArrayList" == intList.class.name
assert 3 == intList.size()

// Coerce ArrayList to int[]
def intArray = intList as int[]
assert "java.util.ArrayList" != intArray.class.name
// Notice we can use int[].length
assert 3 == intArray.length

// Create object from list
def date = [109, 8, 6] as Date
assert 2009 == date[Calendar.YEAR]
assert 8 == date[Calendar.MONTH]
assert 6 == date[Calendar.DATE]

// Treat closure as interface impl
def l = []
def t = new Thread({l << 4} as Runnable)
t.start()
t.join()
assert l == [4]

// Treat map as interface impl
def i = []
def t2 = new Thread([run: {i << 5}] as Runnable)
t2.start()
t2.join()
assert i == [5]

println("All assertions passed")
