/*
 * Copyright 2014 Alessandro Lacava.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import sbt.Keys._
import sbt._

object Publishing extends SonatypePublishing(MoneyBuild) {
  val projectUrl = "https://github.com/lambdista/money"
  val developerId = "lambdista"
  val developerName = "Alessandro Lacava"
  val developerUrl = "http://www.alessandrolacava.com"
  val licenseName = "Apache License, Version 2.0"
  val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
}

abstract class SonatypePublishing(build: Build) {

  val ossSnapshots = "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
  val ossStaging = "Sonatype OSS Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2/"

  val projectUrl: String
  val developerId: String
  val developerName: String
  val developerUrl: String
  val licenseName: String
  val licenseUrl: String
  val scmUrl = projectUrl
  val scmConnection = "scm:git:" + scmUrl

  def settings: Seq[Setting[_]] = Seq(
    publishMavenStyle := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    pomIncludeRepository := { _ => false },
    licenses := Seq(licenseName -> url(licenseUrl)),
    homepage := Some(url(projectUrl)),
    pomExtra := (
      <scm>
        <url>{scmUrl}</url>
        <connection>{scmConnection}</connection>
      </scm>
        <developers>
          <developer>
            <id>{developerId}</id>
            <name>{developerName}</name>
            <url>{developerUrl}</url>
          </developer>
        </developers>),
    credentialsSetting,
    publishArtifact in Test := false,
    pomIncludeRepository := (_ => false)
  )

  lazy val credentialsSetting = credentials += {
    Seq("SONATYPE_USER", "SONATYPE_PASS").map(k => sys.env.get(k)) match {
      case Seq(Some(user), Some(pass)) =>
        Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", user, pass)
      case _ =>
        Credentials(Path.userHome / ".ivy2" / ".credentials")
    }
  }
}