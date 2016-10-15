package fr.mdulac.mower.model

import fr.mdulac.mower.model.Command.{Forward, TurnLeft}
import fr.mdulac.mower.model.Direction.North
import org.scalatest.{FlatSpec, Matchers}

class LawnSpec extends FlatSpec with Matchers {

  "Rectangular Lawn" should "assert position (0, 0) is inside" in {
    // Given
    val lawn = RectangularLawn(5, 5)

    // When / Then
    lawn.inside(Position(0, 0)) should be(true)
  }

  it should "assert that upper position is inside" in {
    // Given
    val lawn = RectangularLawn(5, 5)
    val upperPosition = Position(5, 5)

    // When / Then
    lawn.inside(upperPosition) should be(true)
  }

  it should "assert that negative position is not inside" in {
    // Given
    val lawn = RectangularLawn(5, 5)
    val negativePosition = Position(-2, -5)

    // When / Then
    lawn.inside(negativePosition) should be(false)
  }

  it should "assert that overflow position is not inside" in {
    // Given
    val lawn = RectangularLawn(5, 5)
    val negativePosition = Position(6, 4)

    // When / Then
    lawn.inside(negativePosition) should be(false)
  }

  it should "simulate a simple mower moving" in {
    // Given
    val lawn = RectangularLawn(5, 5)
    val mowersAndCommands = List((Mower(Position.monoid.empty, North), List(Forward)))

    // When / Then
    lawn.simulate(mowersAndCommands) should contain only Mower(Position(0, 1), North)
  }

  it should "simulate a mower moving in square" in {
    // Given
    val lawn = RectangularLawn(5, 5)
    val initialPosition = Position(2, 2)
    val squareCommands = List(Forward, TurnLeft, Forward, TurnLeft, Forward, TurnLeft, Forward, TurnLeft)

    val mowersAndCommands = List((Mower(initialPosition, North), squareCommands))

    // When / Then
    lawn.simulate(mowersAndCommands) should contain only Mower(initialPosition, North)
  }

}
