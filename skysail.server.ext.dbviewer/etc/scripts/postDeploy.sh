#!/bin/bash

### cd /home/carsten/.hudson/jobs/skysail.server.ext.dbviewer/workspace/skysail.server.ext.dbviewer/target
cp skysail.server.ext.dbviewer/target/skysail.*[!-sources].jar /home/carsten/paxrunner/pax-runner-1.7.6/conf
cp skysail.server.ext.dbviewer/target/*.zip /var/www/skysail/
