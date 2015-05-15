import sbt.Keys._
import sbt._

object MoneyBuild extends Build {

  lazy val specs2version = "2.4.12"
  lazy val logBackVersion = "1.1.2"
  lazy val projectScalaVersion = "2.11.6"

  def commonSettings = Seq(
    moduleName := "money",
    version := "0.1.0",
    organization := "com.lambdista",
    scalaVersion := projectScalaVersion,
    crossScalaVersions := Seq(projectScalaVersion, "2.10.4"),
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
        |import com.lambdista.money._
        |import com.lambdista.money.Currency._
        |import com.lambdista.money.syntax._
        |val conversion: Conversion = Map(
        |  (GBP, EUR) -> 1.270,
        |  (EUR, USD) -> 1.268,
        |  (GBP, USD) -> 1.611
        |)
        |
        |implicit val converter = Converter(conversion)""".stripMargin
  )

  lazy val money = (project in file(".")
    aggregate(core, examples)
    dependsOn(core, examples)
    settings (commonSettings: _*)
    settings(
    moduleName := "money-root",
    mainClass in(Compile, run) := Some("com.lambdista.money.example.Usage"),
    (unmanagedSourceDirectories in Compile) := Nil,
    (unmanagedSourceDirectories in Test) := Nil,
    publish := {},
    publishLocal := {}
    )
    )

  lazy val core = (project
    settings (commonSettings ++ Publishing.settings: _*)
    settings(
    moduleName := "money",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % logBackVersion
    )
    )
    )


  lazy val examples = (project in file("examples")
    dependsOn core
    settings (commonSettings: _*)
    settings(
    moduleName := "money-examples",
    mainClass in(Compile, run) := Some("com.lambdista.money.example.Usage"),
    publish := {},
    publishLocal := {}
    )
    )
}