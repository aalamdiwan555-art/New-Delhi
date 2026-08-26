#!/bin/sh
set -e
APP_HOME="$(cd "$(dirname "$0")" && pwd)"
export APP_HOME
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
exec java -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
