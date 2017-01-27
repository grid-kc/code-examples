import random

# A generator is really just an iterator
def simple_generator_function():
    yield 1
    yield 2
    yield 3
l = [1,2,3]
ct = 0
# One way to use a generator
for v in simple_generator_function():
    assert l[ct] == v
    ct += 1

# More explicitly
g = simple_generator_function()
assert 1 == next(g)
assert 2 == next(g)
assert 3 == next(g)
try:
    next(g)
    assert False
except StopIteration:
    # When a generator is exhausted, it will
    # raise a StopIteration exception
    assert True

# Using generators to communicate between functions
def get_data():
    return random.sample(range(10), 3)

def consume():
    running_sum = 0
    data_items_seen =0

    while True:
        data = yield
        data_items_seen += len(data)
        running_sum += sum(data)
        print('The running average is {}'.format(running_sum / float(data_items_seen)))

def produce(consumer):
    while True:
        data = get_data()
        print('Produced {}'.format(data))
        consumer.send(data)
        yield

consumer = consume()
consumer.send(None)
producer = produce(consumer)
for _ in range(10):
    print('Producing...')
    next(producer)

print('All assertions passed')
