organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `lagom-scala-playground` = (project in file("."))
  .aggregate(`lagom-scala-playground-api`, `lagom-scala-playground-impl`, `lagom-scala-playground-stream-api`, `lagom-scala-playground-stream-impl`)

lazy val `lagom-scala-playground-api` = (project in file("lagom-scala-playground-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-scala-playground-impl` = (project in file("lagom-scala-playground-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-scala-playground-api`)

lazy val `lagom-scala-playground-stream-api` = (project in file("lagom-scala-playground-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-scala-playground-stream-impl` = (project in file("lagom-scala-playground-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`lagom-scala-playground-stream-api`, `lagom-scala-playground-api`)

// Modified by Max

lagomServiceLocatorPort in ThisBuild := 10001
