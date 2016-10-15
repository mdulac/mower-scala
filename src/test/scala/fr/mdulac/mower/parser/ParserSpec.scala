package fr.mdulac.mower.parser

import fastparse.core.Parsed.Success
import fr.mdulac.mower.model.Command.{Forward, TurnLeft, TurnRight}
import fr.mdulac.mower.model.Direction.{East, North, South, West}
import fr.mdulac.mower.model.{Mower, Position, RectangularLawn}
import org.scalatest.{FlatSpec, Matchers}

class ParserSpec extends FlatSpec with Matchers {

  "Lawn Parser" should "correctly parse a lawn description string with digit" in {
    val mowerDescription = "5 5"
    Parser.lawn.parse(mowerDescription) should be(Success(RectangularLawn(5, 5), mowerDescription.length))
  }

  it should "correctly parse a lawn description string with numbers" in {
    val mowerDescription = "50 60"
    Parser.lawn.parse(mowerDescription) should be(Success(RectangularLawn(50, 60), mowerDescription.length))
  }

  it should "fail if first size in negative" in {
    val mowerDescription = "-5 10"
    Parser.lawn.parse(mowerDescription) shouldNot be(Success(RectangularLawn(-5, 10), mowerDescription.length))
  }

  it should "fail if second size in negative" in {
    val mowerDescription = "5 -10"
    Parser.lawn.parse(mowerDescription) shouldNot be(Success(RectangularLawn(5, -10), mowerDescription.length))
  }

  "Position Parser" should "correctly parse a valid position" in {
    val position = "5 5"
    Parser.position.parse(position) should be(Success(Position(5, 5), position.length))
  }

  it should "fail if it's a invalid position" in {
    val position = "5 -5"
    Parser.position.parse(position) shouldNot be(Success(Position(5, -5), position.length))
  }

  "Direction Parser" should "correctly parse valid directions" in {
    val north = "N"
    val south = "S"
    val east = "E"
    val west = "W"

    Parser.direction.parse(north) should be(Success(North, south.length))
    Parser.direction.parse(south) should be(Success(South, south.length))
    Parser.direction.parse(east) should be(Success(East, south.length))
    Parser.direction.parse(west) should be(Success(West, south.length))
  }

  "Command Parser" should "correctly parse single valid command" in {
    val forward = "A"
    val left = "G"
    val right = "D"

    Parser.command.parse(forward) should be(Success(Forward, left.length))
    Parser.command.parse(left) should be(Success(TurnLeft, left.length))
    Parser.command.parse(right) should be(Success(TurnRight, left.length))
  }

  "Commands Parser" should "correctly parse valid commands" in {
    val commandsDescription = "AGADA"

    Parser
      .commands
      .parse(commandsDescription) should be(Success(List(Forward, TurnLeft, Forward, TurnRight, Forward), commandsDescription.length))
  }

  "Mower Parser" should "correctly parse a valid mower" in {
    val mowerDescription = "5 5 N"

    Parser.mower.parse(mowerDescription) should be(Success(Mower(Position(5, 5), North), mowerDescription.length))
  }

  "Description Parser" should "correctly parse a valid description file" in {
    val description =
      """5 5
        |1 2 N
        |GAGAGAGAA
        |3 3 E
        |AADAADADDA""".stripMargin

    Parser.description.parse(description) should be(
      Success((
        RectangularLawn(5, 5),
        Seq(
          (Mower(Position(1, 2), North), List(TurnLeft, Forward, TurnLeft, Forward, TurnLeft, Forward, TurnLeft, Forward, Forward)),
          (Mower(Position(3, 3), East), List(Forward, Forward, TurnRight, Forward, Forward, TurnRight, Forward, TurnRight, TurnRight, Forward))
        )),
        description.length))
  }

}
