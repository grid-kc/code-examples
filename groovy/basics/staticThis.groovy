class Appender {
  def static add(list, num) {
    list << num
    this
  }
}

def l = []
Appender.add(l, 1).add(l, 2).add(l, 3)
assert [1,2,3] == l

println("All assertions passed")
