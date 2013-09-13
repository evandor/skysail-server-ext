#!/bin/bash

#######################################################################################################
# Meant to be used for continuous deployment on linux box                                             #
#######################################################################################################

### stop existing daemon
java $JAVA_OPTS -cp .:bin/pax-runner-1.8.5.jar org.ops4j.pax.runner.daemon.DaemonLauncher --stop

### start up new one
java $JAVA_OPTS -cp .:bin/pax-runner-1.8.5.jar org.ops4j.pax.runner.daemon.DaemonLauncher \
--startd --clean scan-composite:file:rund.composite \
--log=DEBUG \
