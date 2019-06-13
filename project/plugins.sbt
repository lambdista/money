resolvers += Resolver.url(
  "rtimush/sbt-plugin-snapshots",
  new URL("https://dl.bintray.com/rtimush/sbt-plugin-snapshots/")
)(Resolver.ivyStylePatterns)
resolvers += "Typesafe repository" at "https://dl.bintray.com/typesafe/maven-releases/"

classpathTypes += "maven-plugin"

lazy val scalafmtVersion   = "1.4.0"
lazy val sbtUpdatesVersion = "0.3.3"
lazy val coursierVersion   = "1.0.0"
lazy val revolverVersion   = "0.9.1"
lazy val tutVersion        = "0.6.12"
lazy val scovVersion       = "1.6.0"
lazy val bintrayVersion    = "0.5.4"

addSbtPlugin("io.get-coursier"   % "sbt-coursier"  % coursierVersion)
addSbtPlugin("com.geirsson"      % "sbt-scalafmt"  % scalafmtVersion)
addSbtPlugin("com.timushev.sbt"  % "sbt-updates"   % sbtUpdatesVersion)
addSbtPlugin("io.spray"          % "sbt-revolver"  % revolverVersion)
addSbtPlugin("org.tpolecat"      % "tut-plugin"    % tutVersion)
addSbtPlugin("org.scoverage"     % "sbt-scoverage" % scovVersion)
addSbtPlugin("org.foundweekends" % "sbt-bintray"   % bintrayVersion)


//resolvers ++= Seq(
//  Classpaths.typesafeReleases,
//  Classpaths.sbtPluginReleases,
//  "jgit-repo" at "http://download.eclipse.org/jgit/maven"
//)
//
//lazy val scalafmtVersion = "0.4.10"
//lazy val updatesVersion  = "0.2.0"
//lazy val tutVersion      = "0.4.7"
//lazy val pgpVersion      = "1.0.0"
//lazy val gitVersion      = "0.8.5"
//lazy val sonatypeVersion = "1.1"
//lazy val dogeVersion     = "0.1.5"
//
//addSbtPlugin("com.geirsson" % "sbt-scalafmt" % scalafmtVersion)
//addSbtPlugin("com.timushev.sbt" % "sbt-updates" % updatesVersion)
//addSbtPlugin("org.tpolecat" % "tut-plugin" % tutVersion)
//addSbtPlugin("com.jsuereth" % "sbt-pgp" % pgpVersion)
//addSbtPlugin("com.typesafe.sbt" % "sbt-git" % gitVersion)
//addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % sonatypeVersion)
//addSbtPlugin("com.eed3si9n" % "sbt-doge" % dogeVersion)
