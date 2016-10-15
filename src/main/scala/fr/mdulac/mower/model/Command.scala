package fr.mdulac.mower.model

import monocle.Iso

sealed trait Command

object Command {

  case object Forward extends Command

  case object TurnLeft extends Command

  case object TurnRight extends Command

  val iso: Iso[String, Command] = Iso[String, Command] {
    case "A" => Forward
    case "G" => TurnLeft
    case "D" => TurnRight
  } {
    case Forward => "A"
    case TurnLeft => "G"
    case TurnRight => "D"
  }

}