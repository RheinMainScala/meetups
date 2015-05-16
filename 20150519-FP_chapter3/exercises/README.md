# Chapter 3 Exercises
This folder contains a skeleton scala file for the exercises of chapter 3
as well as Unit tests for these exercises.

###Prerequisites
This folder is set up to use `sbt`, the Scala build tool, to execute the tests.
To install sbt for your platform, visit the [sbt documentation site](http://www.scala-sbt.org/release/tutorial/Setup.html)
and follow the instructions there.

###Usage
Navigate to the `exercises` folder and invoke the sbt console by typing `sbt`.
The first time you launch sbt, this may take a bit.
In the sbt console, simply type `test` and press enter.
This should run all the tests in the project.
Again, upon first launch sbt needs to pull in some dependencies which may take some time.
The tests will fail with errors, as long as you have nothing implemented.
Edit the file `src/main/scala/List.scala` to implement the exercises and re-run the
tests to check the correctness.
