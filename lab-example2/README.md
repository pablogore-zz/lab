### Problema
2. Implement, using foldRight, the following Seq[Int] operations:
• def sum(): Int: calculates the sum of all the elements

• def elem(i: Int): Option[Int]: returns the element for a given i posi-
tion

• def filter(items: Seq[Int]): Seq[Int]: returns a sequence without
the given elements
• def map[A](fn: Int => A): Seq[A]