#!/bin/bash

#######################################################################################################
# to delete                                                                                           #
#######################################################################################################


#### change in bin/pax-run.sh:
###PID=$!
###echo $PID > osgimonitor2.pid


### --log=WARNING \
# -DGEMINI_DEBUG" \
# --clean \
#  -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 \

../bin/pax-run.sh \
--log=INFO \
--vmOptions="\
 -Dfelix.fileinstall.dir=../config \
 -Dfelix.fileinstall.filter=skysail.*.jar|.*\\.cfg \
 -Dfelix.fileinstall.noInitialDelay=true \
 -Dfelix.fileinstall.poll=1000 \
 -Dfelix.fileinstall.log.level=4 \
 -Dlogback.configurationFile=../../../src/main/resources/logback.xml \
 -Dorg.apache.felix.log.storeDebug=true" \
scan-file:file:run_standalone.setup 

PID=$!
echo $PID > osgimonitor.pid

