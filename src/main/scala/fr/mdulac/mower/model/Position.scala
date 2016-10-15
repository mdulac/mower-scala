package fr.mdulac.mower.model

import cats.Monoid
import monocle.{Lens, _}

case class Position(x: Int, y: Int)

object Position {

  val _x: Lens[Position, Int] = Lens[Position, Int](_.x)(x => p => p.copy(x = x))

  val _y: Lens[Position, Int] = Lens[Position, Int](_.y)(y => p => p.copy(y = y))

  implicit val monoid: Monoid[Position] = new Monoid[Position] {
    override def empty = Position(0, 0)

    override def combine(p1: Position, p2: Position) = Position(p1.x + p2.x, p1.y + p2.y)
  }

}