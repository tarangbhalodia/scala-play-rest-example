import sbt.Keys._

name := "car-adverts"
version := "1.0"

scalaVersion := "2.11.6"

// The Play project itself
lazy val root = (project in file(".")).enablePlugins(PlayScala)

routesGenerator := InjectedRoutesGenerator
libraryDependencies ++= Seq(

  // validator library
  "com.wix" %% "accord-core" % "0.7.2",

  //swagger
  "io.swagger" %% "swagger-play2" % "1.5.0",
  "org.webjars" % "swagger-ui" % "2.2.0",

  //json decoder
  "org.julienrf" %% "play-json-derived-codecs" % "3.0",

  //test
  "org.scalatestplus" %% "play" % "1.4.0" % Test
)
