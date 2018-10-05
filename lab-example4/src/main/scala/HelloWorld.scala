import HelloWorld.{Hello, World}
import akka.actor.Actor

object HelloWorld {
  case class Hello()
  case class World()
}

class HelloWorld extends Actor{
  override def receive: Receive = {
    case Hello=> sender ! World()
  }
}
