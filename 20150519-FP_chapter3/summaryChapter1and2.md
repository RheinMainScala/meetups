Summary of Chapter 1 and 2
=======================

## Chapter 1 - What is Functional Programming?

**Functional programming :** Only use of pure functions.

**Pure functions:** Functions without side effects (e.g. no exceptions, no mutable states).

**Advantages of FP:** modularity, testability, parallelization, generalization.

**Referentially transparent expressions:** in a program, they can be replaced by their result without changing the  meaning of the program.

## Chapter 2 - Getting Started

**Scala:** modules, objects, methods, val, Unit, scalac, REPL.

### Recursivity

**Recursive call in tail position:** caller does nothing other than return the value
of the recursive call.  Scala automatically compiles the recursion to iterative loops.

**@annotation.tailrec:** ask the compiler to verify it's at tail call (and it can compiled with tail call optimization.)

```Scala
@annotation.tailrec
def tailRecursive(a:Int, b:Int) = {
	[...]
	tailRecursive(x,y)
}

def notTailRecursive(a:Int, b:Int) = {
	[...]
	1 + notTailRecursive(x,y)
}
```

### Functions

**Functions in Scala:**

   - are values, they can be assigned to variables, stored in data structures and passed as arguments to functions.
   - can be defined in any block, including another function definition.

**High Order Functions:** Functions that takes one or more functions as argument and may themselves return a function.

**Polymorphic functions (parametric polymorphism):**  functions written without specifying any specific type (there is an abstraction over the type).

```Scala
def monomorphicFct(s: Array[String], key: String): Int = ...
def polymorphicFct[A](s: Array[A], p: A => Boolean): Int = ...```
```

**Anonymous functions (=function literals):** unnamed functions. e.g. :
```Scala
findFirst(Array(7, 9),  (x: Int) => x == 9)

val doSomething = (a:Int, b:Int) => a * b
doSomething(42, 24)
```

**Partial application:** fix a number of arguments to a function and produce another function of smaller arity. e.g.  (here we fix 'a'):
```Scala
def partial1[A,B,C](a: A, f: (A,B) => C): B => C
```

**Currying:** converts a function that takes multiple arguments into a function that takes just a single argument and returns another function.
```Scala
def curry[A,B,C](f: (A, B) => C): A => (B => C)
```
