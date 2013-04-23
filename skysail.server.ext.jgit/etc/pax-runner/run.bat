@echo off

rem #######################################################################################################
rem # Meant to be used for testing during development. Navigate to pax-runner folder of your checked-out  #
rem # skysail project and run local.sh in a terminal                                                      #
rem #                                                                                                     #
rem # You need to configure the path to your local pax-runner installation in the first line!             #
rem #######################################################################################################

C:\tools\pax-runner-1.7.6\bin\pax-run.bat ^
 --http.proxyHost=192.168.11.140 ^
 --http.proxyPort=8080 ^
 --log=INFO ^
 --org.ops4j.pax.url.mvn.repositories=https://oss.sonatype.org/content/groups/public,https://repository.apache.org/content/groups/public/,http://download.eclipse.org/rt/eclipselink/maven.repo,https://maven.alfresco.com/nexus/content/groups/public/ ^
 --vmOptions="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Dfelix.fileinstall.dir=../config,../../../target -Dfelix.fileinstall.filter=skysail.*.jar|.*\\.cfg -Dfelix.fileinstall.noInitialDelay=true -Dfelix.fileinstall.poll=1000 -Dfelix.fileinstall.log.level=4 -DGEMINI_DEBUG -Dlogback.configurationFile=../../../src/main/resources/logback.xml -Dorg.apache.felix.log.storeDebug=true" ^
 scan-composite:file:run.composite


