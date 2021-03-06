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

// Additionally, it's totally valid to use heredoc syntax!
word1 = 'reticent'
word2 = 'perchance'
word3 = 'thrill'
superLongMissive = """I am pretty pleased with the length of this message, but to be honest I am somewhat $word1 to send it.
I feel like I could $word2 be judged on its contents, a prospect to which I do not $word3.
Oh well.
Sending.
"""
println superLongMissive

println "All assertions passed"
