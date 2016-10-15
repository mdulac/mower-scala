package fr.mdulac.mower

import fastparse.core.Parsed
import fr.mdulac.mower.parser.Parser

import scala.io.Source.fromInputStream
import scala.util.Try

object Main extends App {

  val file = Try(args(0)).getOrElse("/mowers.txt")

  Option(getClass.getResourceAsStream(file)) match {
    case None =>
      println(s"Error while reading the file $file")
      System.exit(1)

    case Some(stream) =>
      val source = fromInputStream(stream)
      val lines = try source.getLines.mkString("\n") finally source.close()

      Parser.description.parse(lines) match {
        case Parsed.Success((lawn, mowers), _) =>
          println(lawn.simulate(mowers))
          System.exit(0)
        case Parsed.Failure(p, i, _) =>
          println(s"Error while parsing : expected $p at index $i")
          System.exit(1)
      }
  }

}
