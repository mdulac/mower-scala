package fr.mdulac.mower.model

import monocle.Iso

sealed trait Direction {
  def left: Direction

  def right: Direction
}

object Direction {

  case object North extends Direction {
    override def left = West

    override def right = East
  }

  case object South extends Direction {
    override def left = East

    override def right = West
  }

  case object West extends Direction {
    override def left = South

    override def right = North
  }

  case object East extends Direction {
    override def left = North

    override def right = South
  }

  val iso: Iso[String, Direction] = Iso[String, Direction] {
    case "N" => North
    case "S" => South
    case "W" => West
    case "E" => East
  } {
    case North => "N"
    case South => "S"
    case West => "W"
    case East => "E"
  }

}