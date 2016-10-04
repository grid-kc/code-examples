// The result of evaluating a GString depends on whether we
// use values or references in the expression
// For example, here we change the properties of the object itself.
what = new StringBuilder('fence')
assert 'cow jumped fence' == "cow jumped $what"
what.replace(0,5,'moon')
assert 'cow jumped moon' == "cow jumped $what"
// But, if we just change the reference, the same thing will not happen
str = "cow jumped $what"
what = 'turtle'
assert 'cow jumped moon' == str // Aagh should be turtle!!!
// So, if we add another layer of indirection around our substitution,
// life should be good!
def giveMeWhat = { -> what } // Groovy delays evaluation of this until
                             // we need the result
def betterStr = "cow jumped $giveMeWhat"
assert 'cow jumped turtle' == betterStr
// And we can change the reference to what and get what we expect
what = 'tiny bear'
assert 'cow jumped tiny bear' == betterStr
// What Groovy is actually doing here is, instead of
// giveMeWhat.toString(), it is actually doing
// giveMeWhat.call().toString() implicitly, because it is "smart"

println "All assertions passed"
