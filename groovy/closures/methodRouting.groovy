class Handler {
  def f1() { "Handler's f1" }
  def f2() { "Handler's f2" }
}

class Example {
  def f1() { "Example's f1" }
  def f2() { "Example's f2" }

  def routeToHandler(closure) {
    closure.delegate = new Handler()
    closure()
  }
}

def f1() { "Script's f1" }

new Example().routeToHandler() {
  // Calls to methods are routed like so:
  // first, they go to `this`, the object to which the closure is bound
  // if `this` doesn't have the method, then they go to `delegate`
  assert f1() == "Script's f1"
  assert f2() == "Handler's f2"
}

// Note: it's generally not a great idea to set the delegate, because
// other code could depend on the closure that is being modified.
// It is best practice to clone the closure using the 'with' keyword
// HOWEVER, the 'with' keyword reverses the order of routing -- i.e.,
// it is routed first to the object on which you called 'with', and then
// to 'this' object

class CloneExample {
  def routeToHandler(closure) {
    new Handler().with closure
  }
}

def f3() { "Script's f3" }

new CloneExample().routeToHandler() {
  assert f1() == "Handler's f1"
  assert f2() == "Handler's f2"
  assert f3() == "Script's f3"
}

println "All assertions passed"
