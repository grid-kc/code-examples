// We can determine parameter types to a closure
// dynamically at runtime
def examine(closure) {
  def typeList = []
  for (parameterType in closure.parameterTypes) {
    typeList << parameterType
  }
  typeList
}

// Zero parameters
assert [] == examine() { -> }
// One untyped parameter
assert [java.lang.Object] == examine() { }
assert [java.lang.Object] == examine() { it }
// Typed parameter
assert [java.util.Date] == examine() { Date d -> }
// Both typed and untyped parameters
assert [java.lang.String, java.lang.Object] == examine() {String s, v -> }


// There is a special value called "maximumNumberOfParameters"
// that tells how many params a given closure expects
def calculateSalesTax(subtotal, taxComputer) {
  tax = 0
  if (taxComputer.maximumNumberOfParameters == 2) {
    tax = taxComputer(subtotal, 9.00) // meals tax!!!
  } else {
    tax = taxComputer(subtotal)
  }
}
def taxWithDefault = calculateSalesTax(100) { amt, rate ->
  amt * (rate / 100)
}
def taxDefinedInClosure = calculateSalesTax(100) { 0.0505*it }

assert Math.round(taxWithDefault) == 9
assert Math.round(taxDefinedInClosure) == 5

println "All assertions passed"
