import LinkChecker.Result
import Server.{Request, Response}
import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable

object Server{
  case class Request(url:String,depth:Int)
  case class Response(url:String,links:Set[String])
}

class Server  extends Actor{
  val clients: mutable.Map[String, Set[ActorRef]] = collection.mutable.Map[String, Set[ActorRef]]()
  val controllers: mutable.Map[String, ActorRef] = mutable.Map[String, ActorRef]()


  override def receive: Receive = {
    case Request(url,depth) =>
      val controller  = controllers get url
      if(controller.isEmpty){
        controllers+=(url->context.actorOf(Props[LinkChecker](new LinkChecker(url,depth))))
        clients +=(url -> Set.empty[ActorRef])
      }
      clients(url)+= sender

    case Result(url,links) =>
      context.stop(controllers(url))
      clients(url) foreach(_ ! Response(url,links))
      clients-=url
      controllers-=url
  }
}
