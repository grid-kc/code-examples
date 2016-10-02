def examineClosure(closure) { closure() }
examineClosure() {
  println "Inside first closure"
  println "class: ${getClass().name}"
  println "this: $this, super: ${this.getClass().superclass.name}"
  println "owner: $owner, super: ${owner.getClass().superclass.name}"
  println "delegate: $delegate, super: ${delegate.getClass().superclass.name}"
  examineClosure() {
    println "Inside closure inside first closure"
    println "class: ${getClass().name}"
    println "this: $this, super: ${this.getClass().superclass.name}"
    println "owner: " + owner + "super: " + owner.getClass().superclass.name
    println "delegate: " + delegate + "super: " +  delegate.getClass().superclass.name
  }
}
