import sbt.Keys._

name := "car-adverts"
version := "1.0"

scalaVersion := "2.10.7"

// The Play project itself
lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += "org.scalatestplus" %% "play" % "1.4.0" % Test