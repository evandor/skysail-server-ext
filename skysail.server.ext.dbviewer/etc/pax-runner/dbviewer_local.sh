###
# http://felix.apache.org/site/apache-felix-file-install.html
###

/home/carsten/install/pax-runner-1.7.6/bin/pax-run.sh \
--clean \
--log=WARNING \
--vmOptions="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 \
 -Dskysail.confDir=../../ \
 -Dfelix.fileinstall.dir=../../../target \
 -Dfelix.fileinstall.filter=skysail.*.jar|.*\\.cfg \
 -Dfelix.fileinstall.noInitialDelay=true \
 -Dfelix.fileinstall.poll=1000 \
 -Dfelix.fileinstall.log.level=4 \
 -Dlogback.configurationFile=../../../src/main/resources/logback.xml" \
scan-composite:file:dbviewer_local.composite

