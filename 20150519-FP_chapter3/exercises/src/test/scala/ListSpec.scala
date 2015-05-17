
import org.specs2.mutable._
import org.specs2.specification.AllExpectations
import org.specs2.matcher.DataTables
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

/**
 * @author Franck Valentin
 */
@RunWith(classOf[JUnitRunner])
class ListSpec extends Specification with DataTables {

  "The following exercises should be correct" >> {

    "Exercise 3.1: result of match." in {
      (List.answerExercise1 must_== 3)
    }

    "Exercise 3.2: tail" in {
      "list" || "result" |
        List(1) !! Nil |
        List('a', 'b') !! List('b') |
        List('a', 'b', 'c') !! List('b', 'c') |> {
          (list, expected) => List.tail(list) must_== expected
        }
    }

    "Exercise 3.2: tail (with empty list)" in {
      try {
        List.tail(Nil)
        ko
      } catch {
        case e: RuntimeException => e.getMessage() must_== "tail of empty list"
      }
    }

    "Exercise 3.3: setHead." in {
      "list" || "result" |
        List('a') !! List('h') |
        List('a', 'b') !! List('h', 'b') |
        List('a', 'b', 'c') !! List('h', 'b', 'c') |> {
          (list, expected) => List.setHead(list, 'h') must_== expected
        }
    }

    "Exercise 3.3: setHead (with empty list)." in {
      try {
        List.setHead(Nil, 'h')
        ko
      } catch {
        case e: RuntimeException => e.getMessage() must_== "setHead on empty list"
      }
    }

    "Exercise 3.4: drop." in {
      "list" || "n" || "result" |
        Nil !! 0 !! Nil |
        Nil !! 1 !! Nil |
        List('a') !! 0 !! List('a') |
        List('a') !! 1 !! Nil |
        List('a') !! 2 !! Nil |
        List('a', 'b') !! 1 !! List('b') |
        List('a', 'b') !! 2 !! Nil |
        List('a', 'b', 'c', 'd') !! 3 !! List('d') |> {
          (list, n, expected) => List.drop(list, n) must_== expected
        }
    }

    "Exercise 3.5: dropWhile." in {
      "list" || "result" |
        Nil !! Nil |
        List('x') !! List('x') |
        List('a') !! Nil |
        List('a', 'b', 'c') !! Nil |
        List('a', 'b', 'c', 'x') !! List('x') |
        List('a', 'b', 'c', 'x', 'd') !! List('x', 'd') |
        List('x', 'a', 'b', 'c') !! List('x', 'a', 'b', 'c') |> {
          def f(c: Char) = c != 'x'
          (list, expected) => List.dropWhile[Char](list, f) must_== expected
        }
    }

    "Exercise 3.6: init." in {
      "list" || "result" |
        List('a') !! Nil |
        List('a', 'b') !! List('a') |
        List('a', 'b', 'c') !! List('a', 'b') |> {
          (list, expected) => List.init(list) must_== expected
        }
    }

    "Exercise 3.6: init (empty list)" in {
      try {
        List.init(Nil)
        ko
      } catch {
        case e: RuntimeException => e.getMessage() must_== "init of empty list"
      }
    }

    "Exercise 3.9: length with foldRight." in {
      "list" || "length" |
        Nil !! 0 |
        List('a') !! 1 |
        List('a', 'b') !! 2 |
        List('a', 'b', 'c') !! 3 |> {
          (list, length) => List.length(list) must_== length
        }
    }

    "Exercise 3.10: foldLeft." in {
      "list" || "result" |
        Nil !! "prefix_" |
        List('a') !! "prefix_a" |
        List('a', 'b') !! "prefix_ab" |
        List('a', 'b', 'c') !! "prefix_abc" |> {
          def f(b: String, a: Char) = b + a
          (list, result) => List.foldLeft(list, "prefix_")(f) must_== result
        }
    }

    "Exercise 3.11: sum and length with foldLeft." in {
      "list" || "sum" || "product" || "length" |
        Nil !! 0 !! 1 !! 0 |
        List(4, 3, 2, 1) !! 10 !! 24 !! 4 |
        List(4, 3, 2, 0, 1) !! 10 !! 0 !! 5 |> {
          (list, sum, product, length) =>
            (List.sumFoldLeft(list) must_== sum) and
              (List.lengthFoldLeft(list) must_== length)
        }
    }

    "Exercise 3.11: product with foldLeft." in {
      "list" || "sum" || "product" || "length" |
        Nil !! 0 !! 1 !! 0 |
        List(4, 3, 2, 1) !! 10 !! 24 !! 4 |
        List(4, 3, 2, 0, 1) !! 10 !! 0 !! 5 |> {
          (list, sum, product, length) => List.lengthFoldLeft(list) must_== length
        }
    }

    "Exercise 3.12: reverse." in {
      "list" || "reverse" |
        Nil !! Nil |
        List('a') !! List('a') |
        List('a', 'b') !! List('b', 'a') |
        List('a', 'b', 'c') !! List('c', 'b', 'a') |> {
          (list, reverse) => List.reverse(list) must_== reverse
        }
    }

    "Exercise 3.12: reverse with fold." in {
      "list" || "reverse" |
        Nil !! Nil |
        List('a') !! List('a') |
        List('a', 'b') !! List('b', 'a') |
        List('a', 'b', 'c') !! List('c', 'b', 'a') |> {
          (list, reverse) => List.reverseWithFold(list) must_== reverse
        }
    }    
    
    /*
    "Exercises 3.13: foldRight in terms of foldLeft." in {
      "list" || "result" |
        Nil !! "_suffix" |
        List('a') !! "a_suffix" |
        List('a', 'b') !! "ab_suffix" |
        List('a', 'b', 'c') !! "abc_suffix" |> {
          def f(a: Char, b: String) = a + b
          (list, result) => List.foldRightViaFoldLeft(list, "_suffix")(f) must_== result
        }
    }
    */
    
    "Exercise 3.13: foldLeft in terms of foldRight." in {
      "list" || "result" |
        Nil !! "_suffix" |
        List('a') !! "a_suffix" |
        List('a', 'b') !! "ba_suffix" |
        List('a', 'b', 'c') !! "cba_suffix" |> {
          def f(a: String, b: Char):String = b + a
          (list, result) => List.foldLeftViaFoldRight(list, "_suffix")(f) must_== result
        }
    }

    "Exercise 3.14: append in terms of foldLeft." in {
      "list1" || "list2" || "appended" |
        Nil !! Nil !! Nil |
        List('x') !! List('y') !! List('x', 'y') |
        List('x') !! Nil !! List('x') |
        Nil !! List('y') !! List('y') |
        List('u', 'v', 'w') !! List('x', 'y') !! List('u', 'v', 'w', 'x', 'y') |> {
          (list1, list2, appended) =>
            List.appendViaFoldLeft(list1, list2) must_== appended
        }
    }
    
    "Exercise 3.14: append in terms of foldRight." in {
      "list1" || "list2" || "appended" |
        Nil !! Nil !! Nil |
        List('x') !! List('y') !! List('x', 'y') |
        List('x') !! Nil !! List('x') |
        Nil !! List('y') !! List('y') |
        List('u', 'v', 'w') !! List('x', 'y') !! List('u', 'v', 'w', 'x', 'y') |> {
          (list1, list2, appended) =>
            List.appendViaFoldRight(list1, list2) must_== appended
        }
    }

    "Exercise 3.15: concatenation." in {
      "list" || "concatenate" |
        Nil !! Nil |
        List(List('a')) !! List('a') |
        List(List('a', 'b')) !! List('a', 'b') |
        List(List('a'), List('b'), List('c')) !! List('a', 'b', 'c') |
        List(List('a', 'b', 'c'), Nil) !! List('a', 'b', 'c') |
        List(Nil, List('a', 'b', 'c')) !! List('a', 'b', 'c') |
        List(List('a', 'b', 'c'), List('d', 'e', 'f')) !! List('a', 'b', 'c', 'd', 'e', 'f') |> {
          (list, concatenate) => List.concat(list) must_== concatenate
        }
    }

    "Exercise 3.16: add one." in {
      "list" || "result" |
        Nil !! Nil |
        List(41) !! List(42) |
        List(4, 3, 2, 1) !! List(5, 4, 3, 2) |> {
          (list, result) => (List.add1(list) must_== result)
        }
    }

    "Exercise 3.17: double to string." in {
      "list" || "result" |
        Nil !! Nil |
        List(41.99) !! List("41.99") |
        List(4.4, 3.3, 2.2, 1.1) !! List("4.4", "3.3", "2.2", "1.1") |> {
          (list, result) => (List.doubleToString(list) must_== result)
        }
    }

    "Exercise 3.18: map." in {
      "list" || "result" |
        Nil !! Nil |
        List(41.99) !! List("41.99") |
        List(4.4, 3.3, 2.2, 1.1) !! List("4.4", "3.3", "2.2", "1.1") |> {
          (list, result) =>
            {
              def f(i: Double) = i.toString
              (List.map(list)(f) must_== result)
            }
        }
    }

    "Exercise 3.19: filter." in {
      "list" || "result" |
        Nil !! Nil |
        List(1) !! Nil |
        List(0) !! List(0) |
        List(-1, 1, 2, 4, 6, 3, 5) !! List(2, 4, 6) |
        List(2, -1, 1, 4, 3, 5, 6) !! List(2, 4, 6) |> {
          (list, result) =>
            {
              def f(i: Int) = i % 2 == 0
              (List.filter(list)(f) must_== result)
            }
        }
    }

    "Exercise 3.20 flatMap." in {
      "list" || "result" |
        Nil !! Nil |
        List(1) !! List(1, 1) |
        List(0, 1) !! List(0, 0, 1, 1) |
        List(4, 3, 2, 1) !! List(4, 4, 3, 3, 2, 2, 1, 1) |> {
          (list, result) => List.flatMap(list)(i => List(i, i)) must_== result
        }
    }

    "Exercise 3.21: filter with flatMap." in {
      "list" || "result" |
        Nil !! Nil |
        List(1) !! Nil |
        List(0) !! List(0) |
        List(-1, 1, 2, 4, 6, 3, 5) !! List(2, 4, 6) |
        List(2, -1, 1, 4, 3, 5, 6) !! List(2, 4, 6) |> {
          (list, result) =>
            {
              def f(i: Int) = i % 2 == 0
              (List.filterViaFlatMap(list)(f) must_== result)
            }
        }
    }

    "Exercise 3.22: add elts of two lists." in {
      "l1" || "l2" || "result" |
        Nil !! Nil !! Nil |
        List(1, 2) !! Nil !! Nil |
        Nil !! List(0, 1) !! Nil |
        List(4, 3, 2, 1) !! List(40, 30) !! List(44, 33) |
        List(1, 2) !! List(10, 20, 30) !! List(11, 22) |
        List(4, 3, 2, 1) !! List(40, 30, 20, 10) !! List(44, 33, 22, 11) |> {
          (l1, l2, result) => List.addPairwise(l1, l2) must_== result
        }
    }

    "Exercise 3.23: zipWith." in {
      "l1" || "l2" || "result" |
        Nil !! Nil !! Nil |
        List(1, 2) !! Nil !! Nil |
        Nil !! List(0, 1) !! Nil |
        List(4, 3, 2, 1) !! List(1, 2) !! List(4, 6) |
        List(1, 2) !! List(2, 3, 4) !! List(2, 6) |
        List(4, 3, 2, 1) !! List(4, 3, 2, 1) !! List(16, 9, 4, 1) |> {
          val f = (a: Int, b: Int) => a * b
          (l1, l2, result) => List.zipWith(l1, l2)(f) must_== result
        }
    }

    "Exercise 3.24: hasSubsequence." in {
      "l" || "sub" || "result" |
        Nil !! Nil !! true |
        List(1, 2) !! Nil !! true |
        Nil !! List(1) !! false |
        List(1, 2, 3, 4, 5) !! List(1) !! true |
        List(1, 2, 3, 4, 5) !! List(1, 2) !! true |
        List(1, 2, 3, 4, 5) !! List(1, 2, 3) !! true |
        List(1, 2, 3, 4, 5) !! List(1, 2, 3, 4, 5) !! true |
        List(1, 2, 3, 4, 5) !! List(2) !! true |
        List(1, 2, 3, 4, 5) !! List(2, 3) !! true |
        List(1, 2, 3, 4, 5) !! List(5) !! true |
        List(1, 2, 3, 4, 5) !! List(0, 1) !! false |
        List(1, 2, 3, 4, 5) !! List(1, 2, 4) !! false |
        List(1, 2, 3, 4, 5) !! List(3, 4, 5, 6) !! false |
        List(1, 2, 3, 4, 5) !! List(4, 6) !! false |
        List(1, 2, 3, 4, 5) !! List(1, 2, 3, 4, 5, 6) !! false |> {
          (l, sub, result) => List.hasSubsequence(l, sub) must_== result
        }
    }
  }
}
