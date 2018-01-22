#!/usr/bin/env bash


APP_DIR=.

UBER_JAR=`echo ${APP_DIR}/target/*-uber.jar`

FQ_MAIN_CLASS=br.ufba.dcc.wiser.main.TempSensorApplication

USAGE="usage: [--main <main-class>] [<args...>]"

if [ "$1" = "--main" ]; then
  shift;
  if [ $# = 0 ]; then
    echo ${USAGE}
    exit 1;
  fi
  FQ_MAIN_CLASS=$1; shift
fi

# Runs the application
#
# ./app-run.sh

export CLASSPATH=${UBER_JAR}

java ${FQ_MAIN_CLASS} "$*"
