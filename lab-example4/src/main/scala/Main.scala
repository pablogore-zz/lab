import Server.Response
import akka.actor.{Actor, ActorRef, ActorSystem, Props}



object Main extends App{
  println(s"Current Time ${System.currentTimeMillis}")
  val system = ActorSystem("Crawler")
  val receptionist = system.actorOf(Props[Server], "Server")
  val main = system.actorOf(Props[Main](
    new Main(receptionist, "https://www.redlink.com.ar/", 1)), "BBCActor"
  )


}


class Main(receptionist: ActorRef, url: String, depth: Integer) extends Actor {

  override def receive: Receive = {
    case Response(root, links) =>
      println(s"Root: $root")
      println(s"Links: ${links.toList.sortWith(_.length < _.length).mkString("\n")}")
      println("=========")
      println(s"Current Time ${System.currentTimeMillis}")
  }
}