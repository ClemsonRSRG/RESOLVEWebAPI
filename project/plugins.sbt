// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/ivy-releases/"

// SBT Plugin for Play Framwork 2
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.2")

// SBT Plugin for (License) Headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.6.0")

// SBT Plugin for Java Formatter
addSbtPlugin("com.lightbend.sbt" % "sbt-java-formatter" % "0.5.1")

// SBT Plugin for SASS
addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.11")
