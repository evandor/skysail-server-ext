#!/bin/bash

#######################################################################################################
#######################################################################################################

### --log=WARNING \
PID=$(cat skysail-osgimonitor/osgimonitor.pid)
kill -9 $PID

PID=$(cat skysail-osgimonitor/osgimonitor2.pid)
kill -9 $PID



