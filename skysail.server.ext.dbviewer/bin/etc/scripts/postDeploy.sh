#!/bin/bash

cd /home/carsten/.hudson/jobs/skysail.server.ext.dbviewer/workspace/skysail.server.ext.dbviewer/target
### cp skysail.*[!-sources].jar /home/carsten/paxrunner/pax-runner-1.8.5/conf/
cp skysail-*.zip /home/carsten/paxrunner/pax-runner-1.8.5/
cp *.zip /var/www/skysail/

cd /home/carsten/paxrunner/pax-runner-1.8.5/

###./skysail-dbviewer/stop_standalone.sh

rm -rf skysail-dbviewer
unzip skysail-dbviewer.zip

### copy secrets and configuration
cp secrets.cfg skysail-dbviewer/config
cp de.twenty11.skysail.server.ext.dbviewer.cfg skysail-dbviewer/config

### run standalone
cd skysail-dbviewer
./rund.sh

### doing this to avoid build failure on jenkins
cd
