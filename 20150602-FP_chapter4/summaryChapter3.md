# Summary of Chapter 3 and other stuff


## Functional Data Structures
- Operated on using only pure functions (don't change the data in place),
- are immutable,
- modified by using data sharing to avoid extra copying of the data.
- Advantages:
	- no need for pessimistic copying,
	- data sharing may lead to more efficient implementation.


## Pattern Matching

```Scala
	// Similar to a switch statement

	// General structure
	val result = expression match {
		case ... => ...
		case ... => ...
	}
	
	// Literals
		case 1     => "one"
		case "one" => new MyClass() 

	// To match anything
		case _ => ...

	// Variables (begins with a lowercase letter or underscore)
		val a = 3
		expr match {
			case 0   => 0
			case `a` => ... // matched if expr==3
			case a   => a + 1 // here a is the value of expr
		}

	// Data constructors (case classes)
		case List(1,2) => ...
		
		// matches if the list has 3 elements and the middle one is 2.
		case List(a,2,c) => a + c
		case List(_,2,_) => // I don't care about a and b
		 
		// head :: tail is the same as ::(head, tail), see scala.collection.immutable.::
		case head :: tail => ...

		case MyCaseClass(v) => ...
		case List(MyCaseClass(v1), MyCaseClass(v2)) => v1 + v2

	// Guards and |
		case List(a,b) if a != b => ...
		case 1 | 2 | 3           => ...
	
	// Typed patterns
		case i:Int           => i * 2
		case s:String        => s.toLowerCase
		case mcc:MyCaseClass => mcc.foo()

	// Aliases (to bind the pattern matching to a variable)
	expr match {
		case listWithTwo @ List(_,2,_) => listWithTwo
		case listWithThree @ List(_,3,_) => listWithThree
		case oneElt @ List(_) => oneElt
		case _ => ...
	}
	

```

## Traits

```Scala
// Similar to Java interfaces.
trait MyTraitA {
  def fooA1: String
  def fooA2: Int
}

// Can be partially implemented.
trait MyTraitB {
  var aVar:Int
  val aVal: Int = 42
  def fooB: String = "aVal = " + aVal
  def fooB2: Float

}

// Can be parameterized ([A,B]).
trait MyTraitC[A, B] {
  def fooC(a: A): B
}

// Can extend other traits.
trait MyTraitD[A] extends MyTraitA with MyTraitB with MyTraitC[A, String] {
  def fooA1 = "string from MyTraitD"
  override def fooB = "Override method from MyTraitB"
}

// Can extend classes
abstract class MyAbstractClass {
  def fooAbs(p: Float): Float
}

trait MyTraitE extends MyAbstractClass

val example = new MyTraitE() {
  def fooAbs(p: Float): Float = p * 2
}


// Cannot have constructor parameters.
// trait TraitDoesntCompile(arg:Int)

// Can be extended by classes or traits.
case class ClassA() extends MyTraitD[Char] {
  var aVar = 0
  def fooA2 = 43
  def fooB2 = 1.0f
  def fooC(a: Char) = "a"
}

// Multiple traits and linearization
// See Martin's presentation :)

```

## case classes
```Scala
// Briefly, if you declare:
case class MyCaseClass(i:Int, s:String)

// A new instance can be created without the keyword 'new':
val mcc1 = MyCaseClass(1, "2")

// Can be decomposed in pattern matching:
x match {
	case MyCaseClass(a, "")  => ...
	case MyCaseClass(a,b)  => // do something with a and b.
}

// Automatically define hashcode, equals and toString:
if (MyCaseClass(1,"2") == MyCaseClass(x,y)) ...
println(mcc1) // MyCaseClass(1,2)

// The constructor arguments are (immutable) instance variables:
val i = mcc1.i
val s = mcc1.s
// doesn't compile mcc1.s = "bla"

// Copy method
val mcc2 = mcc1.copy(s="3") // MyCaseClass(1,3)


```

## sealed traits
```Scala
// A sealed trait/class can only be extended in the same file as its declaration.
// (the compiler knows every possible subtypes and can reason about it, especially in pattern matching).
sealed trait RGB

case object Red extends RGB 
case object Green extends RGB
case object Blue extends RGB
	
rgb match {
    case Red   => "red"
    case Green => "green"
}	
// => match may not be exhaustive. It would fail on the following input: Blue	
```

## Variadic Functions
```Scala
def variadicFunction(args: Int*) = ...
variadicFunction(1)
variadicFunction(1,2,3)




