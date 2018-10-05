// *****************************************************************************
// Projects
// *****************************************************************************

lazy val lab =
  project
    .in(file("."))
    .aggregate(`lab-example1`,`lab-example2`,`lab-example4`)
    .settings(settings)
    .settings(
      unmanagedSourceDirectories.in(Compile) := Seq.empty,
      unmanagedSourceDirectories.in(Test) := Seq.empty,
      publishArtifact := false
    )

lazy val `lab-example1` =
  project
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
      )
    )

lazy val `lab-example2` =
  project
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
      )
    )

lazy val `lab-example4` =
  project
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.akkaActor,
        library.ning,
        library.jsoup,
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val akka                     = "2.4.17"
      val ning                     = "1.9.40"
      val jsoup                    = "1.8.3"

      val akkaHttp                 = "10.0.3"
      val akkaHttpJson             = "1.12.0"
      val akkaLog4j                = "1.3.0"
      val akkaPersistenceCassandra = "0.22"
      val akkaPersistenceInmemory  = "2.4.16.0"
      val akkaSse                  = "2.0.0"
      val alpakka                  = "0.6"
      val circe                    = "0.7.0"
      val constructr               = "0.16.1"
      val log4j                    = "2.8"
      val scala                    = "2.12.1"
      val scalaTest                = "3.0.1"
    }

    val akkaActor                  = "com.typesafe.akka"        %% "akka-actor"                   % Version.akka
    val ning                      =  "com.ning"                 % "async-http-client"             % Version.ning
    val jsoup                      = "org.jsoup"                % "jsoup"                         % Version.jsoup

    val akkaClusterSharding        = "com.typesafe.akka"        %% "akka-cluster-sharding"        % Version.akka
    val akkaClusterTools           = "com.typesafe.akka"        %% "akka-cluster-tools"           % Version.akka
    val akkaHttp                   = "com.typesafe.akka"        %% "akka-http"                    % Version.akkaHttp
    val akkaHttpCirce              = "de.heikoseeberger"        %% "akka-http-circe"              % Version.akkaHttpJson
    val akkaHttpTestkit            = "com.typesafe.akka"        %% "akka-http-testkit"            % Version.akkaHttp
    val akkaLog4j                  = "de.heikoseeberger"        %% "akka-log4j"                   % Version.akkaLog4j
    val akkaPersistenceCassandra   = "com.typesafe.akka"        %% "akka-persistence-cassandra"   % Version.akkaPersistenceCassandra
    val akkaPersistenceInmemory    = "com.github.dnvriend"      %% "akka-persistence-inmemory"    % Version.akkaPersistenceInmemory
    val akkaSse                    = "de.heikoseeberger"        %% "akka-sse"                     % Version.akkaSse
    val alpakkaSse                 = "com.lightbend.akka"       %% "akka-stream-alpakka-sse"      % Version.alpakka
    val akkaTestkit                = "com.typesafe.akka"        %% "akka-testkit"                 % Version.akka
    val circeGeneric               = "io.circe"                 %% "circe-generic"                % Version.circe
    val constructr                 = "de.heikoseeberger"        %% "constructr"                   % Version.constructr
    val constructrCoordinationEtcd = "de.heikoseeberger"        %% "constructr-coordination-etcd" % Version.constructr
    val log4jCore                  = "org.apache.logging.log4j" %  "log4j-core"                   % Version.log4j
    val log4jSlf4jImpl             = "org.apache.logging.log4j" %  "log4j-slf4j-impl"             % Version.log4j
    val scalaTest                  = "org.scalatest"            %% "scalatest"                    % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************        |

lazy val settings =
  commonSettings

lazy val commonSettings =
  Seq(
    // scalaVersion and crossScalaVersions from .travis.yml via sbt-travisci
    // scalaVersion := "2.12.1",
    // crossScalaVersions := Seq(scalaVersion.value, "2.11.8"),
    organization := "com.lab",
    licenses += ("Apache 2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    mappings.in(Compile, packageBin) += baseDirectory.in(ThisBuild).value / "LICENSE" -> "LICENSE",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
    resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
  )
