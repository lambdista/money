import sbt.Keys._
import sbt._

name := "money"

version := "1.0"

scalaVersion := "2.11.2"

scalacOptions := Seq(
  "-feature",
  "+language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8",
  "-Ywarn-adapted-args"
)

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-log4j12" % "1.7.7",
  "org.clapper" %% "grizzled-slf4j" % "1.0.2"
)


    