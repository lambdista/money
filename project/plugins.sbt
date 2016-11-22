resolvers ++= Seq(
  Classpaths.typesafeReleases,
  Classpaths.sbtPluginReleases,
  "jgit-repo" at "http://download.eclipse.org/jgit/maven"
)

lazy val scalafmtVersion = "0.4.10"
lazy val updatesVersion  = "0.2.0"
lazy val tutVersion      = "0.4.7"
lazy val pgpVersion      = "1.0.0"
lazy val gitVersion      = "0.8.5"
lazy val sonatypeVersion = "1.1"
lazy val dogeVersion     = "0.1.5"

addSbtPlugin("com.geirsson" % "sbt-scalafmt" % scalafmtVersion)
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % updatesVersion)
addSbtPlugin("org.tpolecat" % "tut-plugin" % tutVersion)
addSbtPlugin("com.jsuereth" % "sbt-pgp" % pgpVersion)
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % gitVersion)
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % sonatypeVersion)
addSbtPlugin("com.eed3si9n" % "sbt-doge" % dogeVersion)
