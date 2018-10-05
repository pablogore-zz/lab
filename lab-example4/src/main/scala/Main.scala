import akka.actor.{Actor, ActorRef, ActorSystem, Props}



object Main extends App{
  val system:ActorSystem  = ActorSystem("HelloWordSystem")
  val helloWorld:ActorRef = system.actorOf(Props[HelloWorld],"HelloWorld")
  helloWorld ! HelloWorld
}