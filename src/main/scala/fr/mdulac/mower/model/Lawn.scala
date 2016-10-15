package fr.mdulac.mower.model

import fr.mdulac.mower.parser.Parser.MowersAndCommands
import monocle._

trait Lawn {

  def inside(p: Position): Boolean

  def simulate(mowers: MowersAndCommands) = for (mowerAndCommands <- mowers) yield {
    val mower = mowerAndCommands._1
    val commands = mowerAndCommands._2

    commands.foldLeft(mower) {
      case (m, c) =>
        val next = m.execute(c)
        if (this.inside(next.position)) next else m
    }

  }

}

case class RectangularLawn(x: Int, y: Int) extends Lawn {

  override def inside(position: Position) =
    position.x >= 0 &&
      position.y >= 0 &&
      position.y <= y &&
      position.x <= x

}

object RectangularLawn {

  val iso: Iso[(Int, Int), RectangularLawn] = Iso[(Int, Int), RectangularLawn] {
    case (xSize, ySize) => RectangularLawn(xSize, ySize)
  } {
    lawn => (lawn.x, lawn.y)
  }

}