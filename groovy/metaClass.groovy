Integer.metaClass.addTo = {
  it + delegate
}
assert 1.addTo(4) == 5

String.metaClass.isPalindrome = {
  delegate == delegate.reverse()
}
assert "lol".isPalindrome()
assert ! "rofl".isPalindrome()

println("All assertions passed")
