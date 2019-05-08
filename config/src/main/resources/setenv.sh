#!/usr/bin/env sh

BASEDIR=$(dirname "$0")

echo "Importing configuration from $BASEDIR/env.properties"

set -o allexport
source "$BASEDIR/env.properties"
set +o allexport
