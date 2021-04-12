lazy val mdocVersion       = "2.2.19"
lazy val scovVersion       = "1.6.1"
lazy val ciReleaseVersion  = "1.5.7"
lazy val sbtUpdatesVersion = "0.5.2"
lazy val scalafmtVersion   = "2.4.2"

addSbtPlugin("org.scalameta"    % "sbt-scalafmt"   % scalafmtVersion)
addSbtPlugin("com.timushev.sbt" % "sbt-updates"    % sbtUpdatesVersion)
addSbtPlugin("org.scalameta"    % "sbt-mdoc"       % mdocVersion)
addSbtPlugin("org.scoverage"    % "sbt-scoverage"  % scovVersion)
addSbtPlugin("com.geirsson"     % "sbt-ci-release" % ciReleaseVersion)
