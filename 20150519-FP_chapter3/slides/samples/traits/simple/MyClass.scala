package samples.traits.simple

class MyClass extends MyTrait[String] {
    // implement method foo from MyTrait
    //   (if it doesn't, MyClass has to be abstract)
    override def foo: String = "bar" + PI
	override def toString: String = foo
}

