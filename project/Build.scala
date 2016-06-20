import sbt.Keys._
import sbt._

object MoneyBuild extends Build {

  lazy val specs2version = "2.4.12"
  lazy val projectScalaVersion = "2.11.8"

  def commonSettings = Seq(
    moduleName := "money",
    organization := "com.lambdista",
    scalaVersion := projectScalaVersion,
    crossScalaVersions := Seq(projectScalaVersion, "2.10.6"),
    (unmanagedSourceDirectories in Compile) <<= (scalaSource in Compile)(Seq(_)),
    (unmanagedSourceDirectories in Test) <<= (scalaSource in Test)(Seq(_)),
    scalacOptions := Seq(
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-Xfatal-warnings",
      "-encoding", "utf8",
      "-deprecation",
      "-unchecked"),
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % specs2version % "test"
    ),
    initialCommands in console :=
      """
        |import money._
        |  val conversion: Conversion = Map(
        |    (EUR, USD) -> 1.13,
        |    (EUR, GBP) -> 0.71,
        |    (USD, EUR) -> 0.88,
        |    (USD, GBP) -> 0.63,
        |    (GBP, EUR) -> 1.40,
        |    (GBP, USD) -> 1.59
        |  )
        |
        |implicit val converter = Converter(conversion)""".stripMargin
  )

  lazy val money = (project in file(".")
    aggregate(core, examples)
    dependsOn(core, examples)
    settings (commonSettings: _*)
    settings(
    moduleName := "money-root",
    mainClass in(Compile, run) := Some("money.example.Usage"),
    (unmanagedSourceDirectories in Compile) := Nil,
    (unmanagedSourceDirectories in Test) := Nil,
    publish := {},
    publishLocal := {}
    )
    )

  lazy val core = (project
    settings (commonSettings ++ Publishing.settings: _*)
    settings(
    moduleName := "money"
    )
    )


  lazy val examples = (project in file("examples")
    dependsOn core
    settings (commonSettings: _*)
    settings(
    moduleName := "money-examples",
    mainClass in(Compile, run) := Some("money.example.Usage"),
    publish := {},
    publishLocal := {}
    )
    )
}