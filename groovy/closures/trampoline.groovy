def factorial

// trampoline turns recursion into iteration under the hood,
// meaning we don't have to worry about blowing up the
// stack. It is SLOW though, so only a little bit useful
factorial = { int number, BigInteger theFactorial ->
  number == 1 ? theFactorial :
    factorial.trampoline(number-1, number * theFactorial)
}.trampoline()

assert factorial(5,1) == 120
assert factorial(5000,1).bitCount() == 24654

println "All assertions passed"
