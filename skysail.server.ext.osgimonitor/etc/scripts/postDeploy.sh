#!/bin/bash

cd /home/carsten/.hudson/jobs/skysail.server.ext.osgimonitor/workspace/skysail.server.ext.osgimonitor/target
### cp skysail.*[!-sources].jar /home/carsten/paxrunner/pax-runner-1.8.5/conf/
cp skysail-*.zip /home/carsten/paxrunner/pax-runner-1.8.5/
cp *.zip /var/www/skysail/

cd /home/carsten/paxrunner/pax-runner-1.8.5/

rm -rf skysail-osgimonitor
unzip skysail-osgimonitor.zip

### copy secrets and configuration
cp secrets.cfg skysail-osgimonitor/config
cp de.twenty11.skysail.server.ext.osgimonitor.cfg skysail-osgimonitor/config
cp de.twenty11.skysail.common.config.Configuration.cfg skysail-osgimonitor/config

### run standalone
cd skysail-osgimonitor
./rund.sh

### doing this to avoid build failure on jenkins
cd
