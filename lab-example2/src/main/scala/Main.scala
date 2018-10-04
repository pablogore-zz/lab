import java.io.{ByteArrayOutputStream, ObjectOutputStream}

import scala.annotation.tailrec
import scala.collection.immutable.Seq
import java.security.MessageDigest

object Main {
  case class Node(someData: Seq[Byte])

  def hash(data: Seq[Byte]): Seq[Byte] =
    MessageDigest
      .getInstance("MD5")
      .digest(data.toArray[Byte]).to[Seq]

  def merkleRootHash(node: Node*): Seq[Byte] = {

    val stream: ByteArrayOutputStream = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(stream)
    oos.writeObject(node)
    oos.close()
    var data: Seq[Byte] = stream.toByteArray.toList
    hash(data)
  }

  /**
    * calculates the sum of all the elements
    * @return Int
    */
  def sum(): Int = ???

  /**
    * returns the element for a given i posi-tion
    *
    * @param i
    * @return
    */
  def elem(i: Int): Option[Int] = ???

  /**
    * returns a sequence without the given elements
    *
    * @param items
    * @return
    */
  def filter(items: Seq[Int]): Seq[Int] = ???

  /**
    *
    * @param fn
    * @tparam A
    * @return
    */
  def map[A](fn: Int => A): Seq[A] = ???


  def main(args: Array[String]): Unit = {
    var root = merkleRootHash(
      Node(Seq(1, 1)),
      Node(Seq(0, 8)),
      Node(Seq(7, 10)),
      Node(Seq(2, 3)),
      Node(Seq(1)))

    println( root    )

    val sum = root.fold(0.0)(_ + _)
    print(sum)
  }


}
