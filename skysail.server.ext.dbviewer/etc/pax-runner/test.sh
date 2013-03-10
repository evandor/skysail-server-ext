#######################################################################################################
# Meant to be used for testing during development. Navigate to pax-runner folder of your checked-out  #
# skysail project and run local.sh in a terminal                                                      #
#                                                                                                     #
# You need to configure the path to your local pax-runner installation in the first line!             #
#######################################################################################################

java $JAVA_OPTS -cp .:bin/pax-runner-1.8.5.jar org.ops4j.pax.runner.Run \
--log=DEBUG \
--vmOptions="\
 -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 \
 -Dfelix.fileinstall.dir=\
../config,\
../../../../../skysail-server/skysail.server/target,\
../../../../skysail.server.ext.dbviewer/target\
 -Dfelix.fileinstall.filter=skysail.*.jar|.*\\.cfg \
 -Dfelix.fileinstall.noInitialDelay=true \
 -Dfelix.fileinstall.poll=1000 \
 -Dfelix.fileinstall.log.level=4 \
 -DGEMINI_DEBUG \
 -Dlogback.configurationFile=../../../src/main/resources/logback.xml \
 -Dorg.apache.felix.log.storeDebug=true" \
scan-composite:file:test.composite


