name         := "lld-scala-repo"
organization := "com.example"
scalaVersion := "2.13.14"

libraryDependencies ++= Seq(
  "com.google.inject"    %  "guice"              % "7.0.0",
  "com.typesafe"         %  "config"             % "1.4.3",
  "org.scalatest"        %% "scalatest"          % "3.2.18" % Test
)
