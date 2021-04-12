import sbt._

object Dependencies {
  lazy val projectScalaVersion = "2.13.5"

  // Versions
  lazy val specs2version = "4.10.6"

  // Libraries
  val specs2 = "org.specs2" %% "specs2-core" % specs2version % "test"

  // Projects
  val coreDeps = Seq(specs2)
}
