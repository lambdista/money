import Dependencies._

import com.typesafe.sbt.SbtGhPages.GhPagesKeys._
import com.typesafe.sbt.SbtSite.SiteKeys._
import UnidocKeys._

lazy val projectScalaVersion = "2.12.0"

lazy val commonSettings = Seq(
  moduleName := "money",
  organization := "com.lambdista",
  scalaVersion := projectScalaVersion,
  crossScalaVersions := Seq(projectScalaVersion, "2.11.8", "2.10.6"),
  scalacOptions := Seq(
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-Xfatal-warnings",
    "-encoding",
    "utf8",
    "-deprecation",
    "-unchecked"
  ),
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

lazy val noPublishSettings =
  Seq(publish := (), publishLocal := (), publishArtifact := false)

lazy val money = (project in file("."))
  .enablePlugins(GitBranchPrompt)
  .aggregate(core, examples)
  .dependsOn(core, examples)
  .settings(commonSettings: _*)
  .settings(
    moduleName := "money-root",
    mainClass in (Compile, run) := Some("money.example.Usage"),
    (unmanagedSourceDirectories in Compile) := Nil,
    (unmanagedSourceDirectories in Test) := Nil,
    publish := {},
    publishLocal := {}
  )

lazy val core =
  (project in file("core"))
    .settings(moduleName := "money")
    .settings(commonSettings)
    .settings(Publishing.settings)

lazy val examples = (project in file("examples"))
  .dependsOn(core)
  .settings(
    moduleName := "money-examples",
    mainClass in (Compile, run) := Some("money.example.Usage")
  )
  .settings(commonSettings)
  .settings(noPublishSettings)

lazy val docSettings = tutSettings ++ site.settings ++ ghpages.settings ++ unidocSettings ++ Seq(
    site.addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), "api"),
    ghpagesNoJekyll := false,
    git.remoteRepo := "https://github.com/lambdista/config",
    unidocProjectFilter in (ScalaUnidoc, unidoc) := inAnyProject -- inProjects(examples),
    includeFilter in makeSite := "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.svg" | "*.js" | "*.swf" | "*.yml" | "*.md"
  )

lazy val docs = (project in file("docs"))
  .dependsOn(core)
  .settings(commonSettings)
  .settings(noPublishSettings)
  .settings(docSettings)
  .settings(moduleName := "config-docs", tutSourceDirectory := file("docs/src/tut"), tutTargetDirectory := file("."))