#!/bin/sh

JAVA_HOME=$JAVA_HOME
JAVA_BIN="$JAVA_HOME/bin/java"
VM_OPTS="-Xms512m -Xmx2048m"
SPB_OPTS="" #"--spring.profiles.active=dev"
APP_LOCATION="$(eval pwd)/../target/springapm.jar"
APP_NAME="springapm"
PID_CMD="ps -ef |grep $APP_NAME |grep -v grep |awk '{print \$2}'"
PID=$(eval $PID_CMD)

start() {
  echo "=============================start=============================="
 if [[ -n $PID ]]; then
    echo "$APP_NAME is already running,PID is $PID"
 else
    nohup $JAVA_BIN $VM_OPTS -jar $APP_LOCATION $SPB_OPTS >/dev/null 2>&1 &
    echo "nohup $JAVA_HOME $VM_OPTS -jar $APP_LOCATION $SPB_OPTS >/dev/null 2>&1 &"
    PID=$(eval $PID_CMD)
    if [[ -n $PID ]]; then
       echo "Start $APP_NAME successfully,PID is $PID"
    else
       echo "Failed to start $APP_NAME !!!"
    fi
 fi
 echo "=============================start=============================="
}

stop() {
 echo "=============================stop=============================="
 if [[ -n $PID ]]; then
    kill -15 $PID
    sleep 5
    PID=$(eval $PID_CMD)
    if [[ -n $PID ]]; then
      echo "Stop $APP_NAME failed by kill -15 $PID,begin to kill -9 $PID"
      kill -9 $PID
      sleep 2
      echo "Stop $APP_NAME successfully by kill -9 $PID"
    else
      echo "Stop $APP_NAME successfully by kill -15 $PID"
    fi
 else
    echo "$APP_NAME is not running!!!"
 fi
 echo "=============================stop=============================="

 status
}

restart() {
 echo "=============================restart=============================="
  stop
  start
  echo "=============================restart=============================="
}

status() {
  echo "=============================Status=============================="
  echo "Find Current PID[$PID] for Application[$APP_NAME]"
  if [[ -n $PID ]]; then
    echo "$APP_NAME is running, PID is $PID"
  else
    echo "$APP_NAME is stopped."
  fi
echo "=============================Status=============================="
}

info() {
 echo "=============================info=============================="
  echo "APP_LOCATION: $APP_LOCATION"
  echo "APP_NAME: $APP_NAME"
  echo "JAVA_HOME: $JAVA_HOME"
  echo "VM_OPTS: $VM_OPTS"
  echo "SPB_OPTS: $SPB_OPTS"
  echo "=============================info=============================="

  status
}

help() {
   echo "start: start server"
   echo "stop: shutdown server"
   echo "restart: restart server"
   echo "status: display status of server"
   echo "info: display info of server"
   echo "help: help info"
}

case $1 in
start)
    start
    ;;
stop)
    stop
    ;;
restart)
    restart
    ;;
status)
    status
    ;;
info)
    info
    ;;
help)
    help
    ;;
*)
    help
    ;;
esac
exit $?