
# Summary of Chapter 6 - Purely functional state

## Using side effects for states
The problem with: 

```Scala
val rng = new scala.util.Random
```

It uses side effect: not easily testable, composable, modularized and parallelized.

## States without side effects

The solution is to return the new state along with the result:
```Scala
// Simple Random Number Generator (our state):
case class SimpleRNG(seed:Long) extends RNG {
	def nextInt: (Int, RNG) = ...
}

def double(rng: RNG): (Double, RNG) = ...

val rng1 = SimpleRNG(42)
val (i1, rng2) = rng1.nextInt
val (i2, rng3) = rng2.nextInt
val (i3, rng4) = rng3.nextInt

val (d1, rng5) = double(rng4)
val (d2, rng6) = double(rng5)
val (d3, rng7) = double(rng6)
```

## Improving the API
Common pattern:
```Scala
RNG => (A, RNG)  // Called  state actions or state transitions.
```

To simplify the API:
```Scala
type Rand[+A] = RNG => (A, RNG)

def map[A,B](s: Rand[A])(f: A => B): Rand[B]
def sequence[A](fs: List[Rand[A]]): Rand[List[A]]
def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B]
```

## Generally speaking
```Scala
type State[S,+A] = S => (A,S) //  computation that carries some state along, or state action, state transition. 

// or:
case class State[S,+A](run: S => (A,S)) {
	def map[B](f: A => B): State[S, B] = ...
	def flatMap[B](f: A => State[S, B]): State[S, B] = ...
}

```
