#######################################################################################################
# Meant to be used for testing during development. Navigate to pax-runner folder of your checked-out  #
# skysail project and run local.sh in a terminal                                                      #
#                                                                                                     #
# You need to configure the path to your local pax-runner installation in the first line!             #
#######################################################################################################

### --log=WARNING \
# -DGEMINI_DEBUG" \
# --clean \
#  -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 \

/home/carsten/install/pax-runner-1.7.6/bin/pax-run.sh \
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
