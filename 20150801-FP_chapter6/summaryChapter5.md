


# Summary of Chapter 5

## strictness vs non-strictness

#### Strict functions always evaluates their arguments (default behaviour in Scala).

```Scala
def myStrictFunction(strictParam1:Int, strictParam2:Int)
myStrictFunction(42, expensiveComputation())
```

A strict function takes its arguments **"by value"**.


#### Non-strict functions may choose not to evaluate one or more of its arguments.

```Scala
def myNonStrictFunction(strictParam1: Int, nonStrictParam2: => Int) = {
  ...
  if (f(nonStrictParam2)) // nonStrictParam2 evaluated here...
    nonStrictParam2       // ...and here...
  else
    nonStrictParam2*2     // ...and here.
}

myNonStrictFunction(42, expensiveComputation())
```

A non-strict function takes its arguments **"by name"**.
The unevaluated form of an expression is called a **thunk**,


### Laziness
```Scala
lazy val a = ... // delay evaluation of the right-hand side of that lazy val declaration
                 // until it’s first referenced, then cache the result.
                 
def myNonStrictFunction(strictParam1: Int, nonStrictParam2: => Int) = {
  lazy val a = nonStrictParam2
  ...
  if (f(a))  // Only evaluated here.
    a       
  else
    a*2 
}
``` 

### Streams

```Scala
sealed trait Stream[+A]
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]
``` 

Separation of concerns:

[...]With Stream, we’re able to build up a computation that produces a sequence of elements
without running the steps of that computation until we actually need those elements.[...]

For example:
```Scala
map, filter , append, flatMap, foldRight, ...  
val ones: Stream[Int] = Stream.cons(1, ones)
``` 

are incremental: the results are only generated when requested.
[...] we can call these functions one after another without fully instantiating the intermediate results.[...]

