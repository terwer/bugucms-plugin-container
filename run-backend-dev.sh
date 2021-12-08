#!/bin/sh
APP_PATH=$WORKSPACE/target
APP_VERSION=2.1.1
APP_NAME=bugucms-plugin-container-${APP_VERSION}.jar
cd ${APP_PATH}/target
echo "Start Application ${APP_NAME}"
setsid java -jar -Dspring.profiles.active=pro ${APP_NAME} > nohup.out 2>&1 &
echo $!
echo Start Success!