#!/bin/bash

cd /home/carsten/.hudson/jobs/skysail.server.ext.osgimonitor/workspace/skysail.server.ext.osgimonitor/target
cp skysail.*[!-sources].jar /home/carsten/paxrunner/pax-runner-1.7.6/conf/
cp *.zip /var/www/skysail/

### doing this to avoid build failure on jenkins
cd
