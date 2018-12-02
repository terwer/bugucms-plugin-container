#!/bin/bash
cd /mnt/c/Users/Terwer/IdeaProjects/bugucms-plugin-container/target
rm *.war*
mv ROOT.zip ROOT.war
scp ROOT.war /mnt/d/Software/apache-tomcat-9.0.13/webapps