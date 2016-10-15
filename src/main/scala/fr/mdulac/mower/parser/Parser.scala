package fr.mdulac.mower.parser

import fr.mdulac.mower.model._

object Parser {

  import fastparse.all._

  type MowersAndCommands = Seq[(Mower, List[Command])]

  private val num: P[Int] = P(CharIn('0' to '9').rep(1)).!.map(_.toInt)
  private val space: P[Unit] = P(" ")
  private val breakLine: P[Unit] = P("\n")
  private val coordinates: P[(Int, Int)] = P(num ~ space ~ num)
  private[parser] val command: P[Command] = P(CharIn("AGD")).!.map(Command.iso.get)

  val direction: P[Direction] = P(CharIn("NSEW")).!.map(Direction.iso.get)

  val commands: P[List[Command]] = P(command.rep(1)).map(_.toList)

  val lawn: P[Lawn] = coordinates.map(RectangularLawn.iso.get)

  val position: P[Position] = coordinates.map { case (x, y) => Position(x, y) }

  val mower: P[Mower] = P(position ~ space ~ direction).map { case (p, d) => Mower(p, d) }

  private val mowersAndCommands: P[MowersAndCommands] = P(mower ~ breakLine ~ commands).rep(1, sep = breakLine)

  val description: P[(Lawn, MowersAndCommands)] = P(Start ~ lawn ~ breakLine ~ mowersAndCommands ~ End)

}
