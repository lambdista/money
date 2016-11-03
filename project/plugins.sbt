resolvers ++= Seq(
  Classpaths.typesafeReleases,
  Classpaths.sbtPluginReleases,
  "jgit-repo" at "http://download.eclipse.org/jgit/maven"
)

lazy val scalafmtVersion = "0.4.8"
lazy val updatesVersion  = "0.2.0"
lazy val tutVersion      = "0.4.4"
lazy val unidocVersion   = "0.3.3"
lazy val ghpagesVersion  = "0.5.4"
lazy val siteVersion     = "0.8.2"
lazy val pgpVersion      = "1.0.1"
lazy val gitVersion      = "0.8.5"

addSbtPlugin("com.geirsson" % "sbt-scalafmt" % scalafmtVersion)
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % updatesVersion)
addSbtPlugin("org.tpolecat" % "tut-plugin" % tutVersion)
addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % unidocVersion)
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % ghpagesVersion)
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % siteVersion)
addSbtPlugin("com.jsuereth" % "sbt-pgp" % pgpVersion)
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % gitVersion)