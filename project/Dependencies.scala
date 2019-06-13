import sbt._

object Dependencies {
  lazy val projectScalaVersion = "2.13.0"

  // Versions
  lazy val specs2version = "4.5.1"

  // Libraries
//  val compPlugin = compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  val specs2     = "org.specs2" %% "specs2-core" % specs2version % "test"

  // Projects
  val coreDeps = Seq(specs2)
}
