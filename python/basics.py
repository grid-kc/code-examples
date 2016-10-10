# String formatting
end_state = 'a, b, c'

assert '{0}, {1}, {2}'.format('a', 'b', 'c')
assert 'a, b, c' == '{}, {}, {}'.format('a', 'b', 'c')

print "All assertions passed"
