import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-demo"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaEbean,
    "org.apache.commons" % "commons-lang3" % "3.1",
	"postgresql" % "postgresql" % "9.1-901-1.jdbc4"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
