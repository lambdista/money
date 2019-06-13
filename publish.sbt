ThisBuild / organization := "com.lambdista.money"
ThisBuild / organizationName := "lambdista"
ThisBuild / organizationHomepage := Some(url("https://www.alessandrolacava.com/"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/lambdista/money"),
    "scm:git@github.com:lambdista/money.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "lambdista",
    name  = "Alessandro Lacava",
    email = "alessandrolacava@gmail.com",
    url   = url("https://www.alessandrolacava.com/")
  )
)

ThisBuild / description := "Scala DSL for money-related operations."
ThisBuild / homepage := Some(url("https://github.com/lambdista/money"))

ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / publishMavenStyle := true

ThisBuild / licenses := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))

ThisBuild / bintrayReleaseOnPublish := true

ThisBuild / bintrayPackageLabels := Seq("money", "currency", "purely functional", "type safe", "scala")

