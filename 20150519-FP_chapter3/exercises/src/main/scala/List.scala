sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A] (head: A, tail: List[A]) extends List[A]

object List {
  
  /*
   * These are the methods given in the book.
   * The exercises follow further down.
   */
  def sum(l: List[Int]): Int = l match {
    case Nil => 0
    case Cons(h, t) => h + sum(t)
  }
  
  def apply[A] (as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }
  
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  
  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  /*
   * EXERCISES
   * 
   * Fill in the method stubs and use the supplied tests to verify
   */
  
  /*
   * EXERCISE 3.1 
   *
   * What is returned by the following expression?
   * Give your answer as the return value of the function "answerExercise1"
   * val x = List(1,2,3,4,5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42 
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + List.sum(t)
    case _ => 101 
  }
   */
  def answerExercise1(): Int = {
    -1
  }

  /*
   * EXERCISE 3.2
   */
  def tail[A] (l: List[A]): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.3
   */
  def setHead[A] (l: List[A], e: A): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.4
   */
  def drop[A](l: List[A], n: Int): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.5
   */
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.6
   */
  def init[A](l: List[A]): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.9
   */
  def length[A](as: List[A]): Int = sys.error("todo")
  
  /*
   * EXERCISE 3.10
   */
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = sys.error("todo")
  
  /*
   * EXERCISE 3.11
   */
  
  def productFoldLeft(ns: List[Double]) = sys.error("todo")
  
  def sumFoldLeft(l: List[Int]): Int = sys.error("todo")
  
  def lengthFoldLeft(l: List[Int]): Int = sys.error("todo")
  /*
   * EXERCISE 3.12
   */
  def reverse[A] (l: List[A]): List[A] = sys.error("todo")
  
  def reverseWithFold[A] (l: List[A]): List[A] = sys.error("todo")

  /*
   * EXERCISE 3.13
   */
  def foldLeftViaFoldRight[A,B](as: List[A], z: B)(f: (B, A) => B): B = sys.error("todo")
  
  /*
   * EXERCISE 3.14
   */
  def appendViaFoldLeft[A](a1: List[A], a2: List[A]): List[A] = sys.error("todo")
  
  def appendViaFoldRight[A](a1: List[A], a2: List[A]): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.15 (HARD)
   */
  def concat[A] (l1: List[List[A]]): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.16
   */
  def add1 (l: List[Int]): List[Int] = sys.error("todo")
  
  /*
   * EXERCISE 3.17
   */
  def doubleToString (l: List[Double]): List[String] = sys.error("todo")

  /*
   * EXERCISE 3.18
   */
  def map[A,B](as: List[A])(f: A => B): List[B] = sys.error("todo")
  
  /*
   * EXERCISE 3.19
   */
  def filter[A](as: List[A])(f: A => Boolean): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.20
   */
  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = sys.error("todo")

  /*
   * EXERCISE 3.21
   */
  def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.22
   */
  def addPairwise[A](l1: List[A], l2: List[A]): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.23
   */
  def zipWith[A](l1: List[A], l2: List[A]) (f: (A, A) => A): List[A] = sys.error("todo")
  
  /*
   * EXERCISE 3.24 (HARD)
   */
  def hasSubsequence[A] (l1: List[A], l2: List[A]): Boolean = sys.error("todo")
  
}
