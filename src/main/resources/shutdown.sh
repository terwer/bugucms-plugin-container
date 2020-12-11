#!/bin/sh
APP_PATH=$WORKSPACE/target
APP_VERSION=2.1.1
APP_NAME=bugucms-plugin-container-${APP_VERSION}.jar
cd ${APP_PATH}/target
curl -X POST http://121.4.76.184/actuator/shutdown
sleep 5
tpid1=`ps -ef|grep invoice.war|grep -v grep|grep -v kill|awk '{print $2}'`
echo tpid1-$tpid1
if [[ $tpid1 ]]; then
    echo 'Stop Process...'
    kill -15 $tpid1
fi
sleep 5
tpid2=`ps -ef|grep ${APP_NAME}|grep -v grep|grep -v kill|awk '{print $2}'`
echo tpid2-$tpid2
if [[ $tpid2 ]]; then
    echo 'Kill Process!'
    kill -9 $tpid2
else
    echo 'Stop Success!'
fi