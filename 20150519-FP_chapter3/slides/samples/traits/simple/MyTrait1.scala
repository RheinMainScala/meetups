package samples.traits.simple

trait MyTrait1 {
    val PI: Double = 3.1415f 
    var counter: Int = 0
    
	def tMethod1: String = { counter+=1; "implementation of trait method1 (" + counter + ")"; }
	def tMethod2: String
}
