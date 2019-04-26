#!/usr/bin/env sh

export PROJECT_DIR=$1
export DEFAULT_PROPERTIES=default.env.properties
export PROPERTIES=env.properties
export TARGET_DIR="$CATALINA_HOME/bin"

\cp setenv.sh setenv.bat "$TARGET_DIR"

if [ -f "$PROJECT_DIR/$PROPERTIES" ]
then
  echo "Replacing default properties with user defined"
  \awk -F= '!a[$1]++' "$PROJECT_DIR/$PROPERTIES" "$PROJECT_DIR/$DEFAULT_PROPERTIES" > "$TARGET_DIR/$PROPERTIES"
fi

echo "Printing merged env.properties"
cat "$TARGET_DIR/$PROPERTIES"
