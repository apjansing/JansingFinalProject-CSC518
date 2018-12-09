#! /bin/bash
cd /opt/hadoop
sbin/start-dfs.sh
sbin/start-yarn.sh

# https://hub.docker.com/r/apacheapex/sandbox/
docker pull apacheapex/sandbox
docker run -it --name=apex-sandbox -p 50070:50070 -p 8089:8088 apacheapex/sandbox