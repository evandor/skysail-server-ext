#!/bin/bash

cd /home/carsten/.hudson/jobs/skysail.server.ext.dbviewer/workspace/skysail.server.ext.dbviewer/target
cp skysail.*[!-sources].jar /home/carsten/paxrunner/pax-runner-1.7.6/conf
cp *.zip /var/www/skysail/
