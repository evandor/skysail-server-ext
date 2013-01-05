#!/bin/bash

#######################################################################################################
# Meant to be used for testing during development. Navigate to pax-runner folder of your checked-out  #
# skysail project and run local.sh in a terminal                                                      #
#                                                                                                     #
# You need to configure the path to your local pax-runner installation in the first line!             #
#######################################################################################################

../bin/pax-rund-osgimonitor.sh --stop

../bin/pax-rund-osgimonitor.sh \
--startd scan-file:file:/home/carsten/paxrunner/pax-runner-1.8.5/skysail-osgimonitor/rund.setup \
--log=INFO \
