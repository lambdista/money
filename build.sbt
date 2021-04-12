import java.time.LocalDate

import Dependencies._

lazy val projectName = "money"

lazy val commonSettings = Seq(
  moduleName := projectName,
  organization := "com.lambdista",
  description := "Scala DSL for money-related operations.",
  homepage := Some(url("https://github.com/lambdista/money")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer("lambdista", "Alessandro Lacava", "alessandrolacava@gmail.com", url("https://alessandrolacava.com"))
  ),
  scalaVersion := projectScalaVersion,
  crossScalaVersions := Seq(projectScalaVersion, "2.12.8", "2.11.12"),
  resolvers ++= Seq(Resolver.sonatypeRepo("releases"), Resolver.sonatypeRepo("snapshots")),
  scalacOptions := (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 13)) =>
      Seq(
        "-feature",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-encoding",
        "utf8",
        "-deprecation",
        "-unchecked",
        "-Ywarn-unused",
        "-Ywarn-dead-code"
      )
    case _ =>
      Seq(
        "-feature",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-Ypartial-unification",
        "-encoding",
        "utf8",
        "-deprecation",
        "-unchecked",
        "-Ywarn-unused-import",
        "-Ywarn-unused",
        "-Ywarn-dead-code",
        "-Yno-adapted-args"
      )
  }),
  scalafmtOnCompile := true,
  libraryDependencies ++= coreDeps,
  console / initialCommands :=
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

lazy val noPublishSettings = Seq(publish / skip := true)

lazy val money = (project in file("."))
  .aggregate(core, examples)
  .dependsOn(core, examples)
  .settings(moduleName := s"$projectName-root", Compile / run / mainClass := Some("money.example.Usage"))
  .settings(commonSettings)
  .settings(noPublishSettings)

lazy val core =
  (project in file("core"))
    .settings(moduleName := projectName)
    .settings(commonSettings)

lazy val examples = (project in file("examples"))
  .dependsOn(core)
  .settings(moduleName := s"$projectName-examples", Compile / run / mainClass := Some("money.example.Usage"))
  .settings(commonSettings)
  .settings(noPublishSettings)

lazy val docs = (project in file("docs"))
  .dependsOn(core)
  .enablePlugins(MdocPlugin)
  .settings(commonSettings)
  .settings(noPublishSettings)
  .settings(
    moduleName := s"$projectName-docs",
    mdocIn := file("docs/src/mdoc"),
    mdocOut := file("."),
    mdocVariables := Map("YEAR" -> LocalDate.now.getYear.toString)
  )
