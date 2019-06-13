import Dependencies._

lazy val commonSettings = Seq(
  moduleName := "money",
  organization := "com.lambdista",
  scalaVersion := projectScalaVersion,
  version := "0.7.0",
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
  scalafmtConfig := Some(file(".scalafmt.conf")),
  libraryDependencies ++= coreDeps,
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

lazy val noPublishSettings = Seq(skip in publish := true)

lazy val money = (project in file("."))
  .aggregate(core, examples)
  .dependsOn(core, examples)
  .settings(moduleName := "money-root", mainClass in (Compile, run) := Some("money.example.Usage"))
  .settings(commonSettings)
  .settings(noPublishSettings)

lazy val core =
  (project in file("core"))
    .settings(moduleName := "money")
    .settings(commonSettings)

lazy val examples = (project in file("examples"))
  .dependsOn(core)
  .settings(moduleName := "money-examples", mainClass in (Compile, run) := Some("money.example.Usage"))
  .settings(commonSettings)
  .settings(noPublishSettings)

lazy val docs = (project in file("docs"))
  .enablePlugins(TutPlugin)
  .settings(commonSettings)
  .settings(noPublishSettings)
  .settings(
    moduleName := "money-docs",
    tutSourceDirectory := file("docs/src/tut"),
    tutTargetDirectory := file(".")
  )
