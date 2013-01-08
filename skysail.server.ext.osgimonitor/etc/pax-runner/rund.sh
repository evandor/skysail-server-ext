#!/bin/bash

#######################################################################################################
# Meant to be used for continuous deployment on linux box                                             #
#######################################################################################################

### stop existing daemon
../bin/pax-rund-osgimonitor.sh --stop

### start up new one
../bin/pax-rund-osgimonitor.sh \
--startd scan-file:file:/home/carsten/paxrunner/pax-runner-1.8.5/skysail-osgimonitor/rund.setup \
--log=INFO \
