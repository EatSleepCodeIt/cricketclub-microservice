#!/bin/sh
set -e
set -x
export DEBIAN_FRONTEND=noninteractive

cd /opt/webapp/config
sed -i 's/serverhostname/'"$SERVER_HOSTNAME"'/g' application.properties
sed -i 's/eurekahostname/'"$EUREKA_HOSTNAME"'/g' application.properties
sed -i 's/oauthhostname/'"$OAUTH_HOSTNAME"'/g' application.properties
cat application.properties
cd /opt/webapp
java -jar -Xms128m -Xmx128m cloud-config-server.jar  

