#!/usr/bin/env sh

export PROJECT_DIR=$1
export DEFAULT_PROPERTIES=default.env.properties
export PROPERTIES=env.properties
export TARGET_DIR="$CATALINA_HOME/bin"

\cp setenv.sh setenv.bat "$TARGET_DIR"
\cp $DEFAULT_PROPERTIES "$TARGET_DIR/$PROPERTIES"

if [ -f "$PROJECT_DIR/$PROPERTIES" ]
then
  \awk -F= '!a[$1]++' $PROPERTIES "$TARGET_DIR/$PROPERTIES"
fi

echo "Printing merged env.properties"
cat "$TARGET_DIR/$PROPERTIES"
