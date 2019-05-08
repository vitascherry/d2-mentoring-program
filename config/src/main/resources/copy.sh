#!/usr/bin/env sh

BASEDIR=$(dirname "$0")

PROJECT_DIR="$BASEDIR/$1"
DEFAULT_PROPERTIES=default.env.properties
PROPERTIES=env.properties
TARGET_DIR="$CATALINA_HOME/bin"

\cp "$BASEDIR/setenv.sh" "$BASEDIR/setenv.bat" "$TARGET_DIR"

if [ -f "$PROJECT_DIR/$PROPERTIES" ]
then
  echo "Replacing default properties with user defined"
  \awk -F= '!a[$1]++' "$PROJECT_DIR/$PROPERTIES" "$PROJECT_DIR/$DEFAULT_PROPERTIES" > "$TARGET_DIR/$PROPERTIES"
fi

cat "$TARGET_DIR/$PROPERTIES"
