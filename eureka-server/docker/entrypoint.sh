#!/bin/sh
set -e
set -x
export DEBIAN_FRONTEND=noninteractive

cd /opt/webapp
java -jar -Xms256m -Xmx256m eureka-server.jar  

