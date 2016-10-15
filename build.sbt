name := "mower-scala"

version := "1.0"

scalaVersion := "2.11.8"

val monocleVersion = "1.2.2"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.11"
libraryDependencies += "org.typelevel" %% "cats" % "0.7.0"
libraryDependencies += "com.lihaoyi" %% "fastparse" % "0.4.1"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-state" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-refined" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test"
)