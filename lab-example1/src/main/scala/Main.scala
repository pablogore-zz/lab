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

  def main(args: Array[String]): Unit = {
    println(merkleRootHash(
      Node(Seq(1, 1)),
      Node(Seq(0, 8)),
      Node(Seq(7, 10)),
      Node(Seq(2, 3)),
      Node(Seq(1)))
    )
  }
}
