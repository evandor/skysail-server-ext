#!/bin/bash

cd /home/carsten/.hudson/jobs/skysail.server.ext.osgimonitor/workspace/skysail.server.ext.osgimonitor/target
### cp skysail.*[!-sources].jar /home/carsten/paxrunner/pax-runner-1.7.6/conf/
cp skysail-*.zip /home/carsten/paxrunner/pax-runner-1.7.6/
cp *.zip /var/www/skysail/

cd /home/carsten/paxrunner/pax-runner-1.7.6/
rm -rf unzip skysail-osgimonitor
unzip skysail-osgimonitor.zip
cd skysail-osgimonitor
./run_standalone.sh

### doing this to avoid build failure on jenkins
cd
