#/bin/bash

sudo docker stop tomcat
sudo rm -r /home/paul/dproj/**
cd /home/paul/workspace/yiblog-ssm
mvn clean package
cp target/yiblog-ssm.war /home/paul/dproj/ROOT.war
sudo docker start tomcat
