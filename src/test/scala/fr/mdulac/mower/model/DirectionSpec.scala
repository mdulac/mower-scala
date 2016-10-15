package fr.mdulac.mower.model

import org.scalatest.{FlatSpec, Matchers}

class DirectionSpec extends FlatSpec with Matchers {

  "Direction" should "go to correct direction when turn left" in {
    Direction.North.left should be(Direction.West)
    Direction.West.left should be(Direction.South)
    Direction.South.left should be(Direction.East)
    Direction.East.left should be(Direction.North)
  }

  it should "go to correct direction when turn right" in {
    Direction.North.right should be(Direction.East)
    Direction.West.right should be(Direction.North)
    Direction.South.right should be(Direction.West)
    Direction.East.right should be(Direction.South)
  }

}
