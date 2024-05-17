#!/bin/bash
# A menu driven shell script for liquibase
## ----------------------------------
# Step #1: Define variables
# ----------------------------------
EDITOR=vim
PASSWD=/etc/passwd
RED='\033[0;41;30m'
STD='\033[0;0;39m'
original_PWD=$PWD
FILE='liquibase.properties'
databaseUrl=$1
dbUsername=$2
dbPassword=$3
context=$4
component=$5


upgradeAll() {
  echo ""
  echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
  echo "Upgrading the ${component} components database"
  echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
  cd "${original_PWD}" || exit 1

  if [[ -z "${component}" ]] || [[ "${component}" == "" ]]; then
    changLogTableName="dbChangeLog"
  else
    cd "${component}" || exit 1
    changLogTableName="dbChangeLog_${component}"
  fi

  echo ""
  echo "Creating Properties Backup in"
  pwd
#  mkdir saved
#  cp liquibase.properties saved/liquibase.properties

  if [ -z "${dbUsername}" ]; then
    dbUsername="root"
    echo "Using Default dbUsername and dbPassword"
  fi
  if [ -z "${dbPassword}" ]; then
    dbPassword="mysql"
  fi
  if [ -z "${context}" ]; then
    context="prod"
  fi
  echo "Using Context : \"$context\""

  mvn install -f pom-liquibase.xml -Dliquibase.properties=liquibase.properties -Dliquibase.goal=update

  # This checks the exit code of previously run command,
  # so it should be just after executing `mvn` command
  if [[ "$?" -ne 0 ]]; then
    exit 1
  fi

  echo ""
  echo "Replacing Properties Backup in"
  pwd
#  rm -rf liquibase.properties
#  cp saved/liquibase.properties .
#  rm -rf saved/

  echo ""
  echo "Removing the target directories in"
  pwd
  rm -rf target

}

upgradeAll
