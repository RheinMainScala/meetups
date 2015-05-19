package samples.traits.simple

class MyClassA extends MyAbstractSuperClass with MyTrait1 {
	override def tMethod2: String = "implementation of trait method2"
	override def toString: String = PI + "#" + smethod + " # " + tMethod1 + " # " + tMethod2
}

