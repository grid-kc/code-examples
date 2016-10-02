// 'seq' is available inside drive(vehicle), but
// if I had def'd it, that would not be the case
seq = []
def drive(vehicle) {
  vehicle.drive(seq)
  // Check if the vehicle can be anchored
  if (vehicle.metaClass.respondsTo(vehicle, 'anchor')) {
    vehicle.anchor()
  }
}

// Like implementing an interface implicitly
class Truck {
  def drive(seq) {
    seq << 'truck'
  }
}

class Car {
  def drive(seq) {
    seq << 'car'
  }
}

class Boat {
  def drive(seq) {
    seq << 'boat'
  }

  def anchor(seq) {
    'docked'
  }
}

// Duck typing -- if it looks like a duck and
// walks like a duck . . . it's a duck
drive(new Car())
assert seq == ['car']
drive(new Truck())
assert seq == ['car', 'truck']
assert 'docked' == drive(new Boat())
assert seq == ['car', 'truck', 'boat']

println('All assertions passed')
