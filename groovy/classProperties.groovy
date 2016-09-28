class Car {
  // 'final' keyword makes things readonly
  final year
  int miles = 0

  Car(y) { year = y }

  // In order to make 'miles' private, have to override the setter
  private void setMiles(m) {
    throw new IllegalAccessException("Don't mess with my odometer")
  }

  def drive(dist) { if (dist > 0) miles += dist }
}

def car = new Car(1965)

assert car.miles == 0
car.drive(123)
assert car.miles == 123
// 'miles' is private
try {
  car.miles = 0
} catch(IllegalAccessException e) {
  assert true
}
// 'year' is readonly
try {
  car.year
} catch(groovy.lang.ReadOnlyPropertyException e) {
  assert true
}

println("All assertions passed")
