package fr.mdulac.mower.model

import fr.mdulac.mower.model.Command.{Forward, TurnLeft, TurnRight}
import fr.mdulac.mower.model.Direction.{East, North, South, West}
import fr.mdulac.mower.model.Mower._position
import fr.mdulac.mower.model.Position.{_x, _y}
import monocle.Lens

case class Mower(position: Position, direction: Direction) {

  private[model] def forward(step: Int = 1): Mower = direction match {
    case North => _position.set(_y.modify(y => y + step)(position))(this)
    case South => _position.set(_y.modify(y => y - step)(position))(this)
    case East => _position.set(_x.modify(x => x + step)(position))(this)
    case West => _position.set(_x.modify(x => x - step)(position))(this)
  }

  def execute(command: Command): Mower = command match {
    case Forward => this.forward()
    case TurnLeft => this.copy(direction = direction.left)
    case TurnRight => this.copy(direction = direction.right)
  }

}

object Mower {

  val _position: Lens[Mower, Position] = Lens[Mower, Position](_.position)(p => m => m.copy(position = p))

}