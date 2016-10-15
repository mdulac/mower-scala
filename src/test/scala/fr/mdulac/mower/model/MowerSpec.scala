package fr.mdulac.mower.model

import fr.mdulac.mower.model.Direction.{East, North, South, West}
import org.scalatest.{FlatSpec, Matchers}

class MowerSpec extends FlatSpec with Matchers {

  "Mower" should "go to correct position when face North" in {
    Mower(Position(0, 0), North).forward() should be(Mower(Position(0, 1), North))
    Mower(Position(0, 0), North).forward(2) should be(Mower(Position(0, 2), North))
    Mower(Position(4, 5), North).forward(4) should be(Mower(Position(4, 9), North))
  }

  it should "go to correct position when face South" in {
    Mower(Position(0, 0), South).forward() should be(Mower(Position(0, -1), South))
    Mower(Position(0, 0), South).forward(2) should be(Mower(Position(0, -2), South))
    Mower(Position(4, 5), South).forward(4) should be(Mower(Position(4, 1), South))
  }

  it should "go to correct position when face West" in {
    Mower(Position(0, 0), West).forward() should be(Mower(Position(-1, 0), West))
    Mower(Position(0, 0), West).forward(2) should be(Mower(Position(-2, 0), West))
    Mower(Position(4, 5), West).forward(4) should be(Mower(Position(0, 5), West))
  }

  it should "go to correct position when face East" in {
    Mower(Position(0, 0), East).forward() should be(Mower(Position(1, 0), East))
    Mower(Position(0, 0), East).forward(2) should be(Mower(Position(2, 0), East))
    Mower(Position(4, 5), East).forward(4) should be(Mower(Position(8, 5), East))
  }

}
