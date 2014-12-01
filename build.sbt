import sbt.Keys._
import sbt._

name := "money"

version := "1.0"

scalaVersion := "2.11.4"

scalacOptions := Seq(
  "-feature",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:postfixOps",
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8",
  "-Ywarn-adapted-args"
)

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "org.specs2" %% "specs2-core" % "2.4.12" % "test"
)


    