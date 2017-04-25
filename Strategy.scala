/**
  * Created by BanaN on 4/14/2017.
  */

import scala.util.parsing.json._
import play.api.libs.json._

import scala.io.Source

trait Parser {
  def parse(name: String): Unit
}

trait ParseCSV extends Parser {
  override def parse(name: String)
  {
    println("Called CSV's execute()")

    //val bufferedSource = io.Source.fromFile(name)
    val bufferedSource = Source.fromURL(Source.getClass().getResource(name))
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
     // println(cols)
    }
    bufferedSource.close
  }
}

trait ParseJSON extends Parser {
  override def parse(name: String)
  {
    println("Called JSON's execute()")

    val bufferedSource = Source.fromURL(Source.getClass().getResource(name))
    val parsed = Json.parse(bufferedSource)

    println(parsed)

    bufferedSource.close
  }

}

class ParserC {
  self: Parser =>

  def execute(name:String) {
    // ...

    parse(name)
    // ...
  }
}

object StrategyExample extends App {
  val ex1 = new ParserC with ParseCSV
  val ex2 = new ParserC with ParseJSON

  ex1.execute("/test.csv")
  ex2.execute("/test.json")
}