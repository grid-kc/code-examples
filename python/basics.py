# String formatting
end_state = 'a, b, c'

assert 'a, b, c' == '{0}, {1}, {2}'.format('a', 'b', 'c')
assert 'a, b, c' == '{}, {}, {}'.format('a', 'b', 'c')
assert 'c, b, a' == '{2}, {1}, {0}'.format('a', 'b', 'c')
# Unpacking
assert 'c, b, a' == '{2}, {1}, {0}'.format(*'abc')
# Repeating indices
assert 'abracadabra' == '{0}{1}{0}'.format('abra', 'cad')
# Accessing arguments by name
coords = {'latitude': '37N', 'longitude': '-110W'}
assert '37N, -110W' == '{latitude}, {longitude}'.format(**coords)
# Accessing arguments' attributes
c = 4 + 7j
assert 'real: 4.0, imag: 7.0' == 'real: {0.real}, imag: {0.imag}'.format(c)
class Point(object):
    def __init__(self, x, y):
        self.x, self.y = x, y
    def __str__(self):
        return 'Point({self.x}, {self.y})'.format(self=self)

assert 'Point(5, 1)' == str(Point(5,1))

print "All assertions passed"
