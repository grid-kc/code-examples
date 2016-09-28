class Robot {
  def type, height, weight
  def access(location, weight, isFragile) {
    [location, weight, isFragile]
  }
}

r = new Robot(type: 'humanoid', weight: 50, height: 180)
assert r.type == 'humanoid'
assert r.height == 180
assert r.weight == 50

// If there are too many args, then Groovy assumes the first argument is a map
def args = r.access(x: 10, y: 20, z: 30, 5, false)
assert args[0] == [x:10, y:20, z:30]
assert args[1] == 5
assert args[2] == false

// Can also move the map around -- Groovy still knows which argument it is!!!
// I have no idea why anyone would want to do this
def args2 = r.access(5, false, x: 10, y: 20, z: 30)
assert args2[0] == [x:10, y:20, z:30]
assert args2[1] == 5
assert args2[2] == false

// Optional parameters work like Python
def log(x, base=10) {
  Math.log(x) / Math.log(base)
}
assert Math.ceil(log(100)) == 2
assert Math.ceil(log(1000, 10)) == 3
assert Math.ceil(log(1024, 2))== 10


println("All assertions passed")
