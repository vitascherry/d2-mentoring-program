#!/usr/bin/env sh

export PROJECT_DIR=$1

cp setenv.sh setenv.bat $PROJECT_DIR/env.properties $CATALINA_HOME/bin
