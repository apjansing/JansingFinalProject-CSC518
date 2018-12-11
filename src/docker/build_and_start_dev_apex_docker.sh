#!/bin/bash
JANSING_PROJECT_HOME=`(git rev-parse --show-toplevel)`
JANSING_REPO_NAME=`(basename $JANSING_PROJECT_HOME)`

docker build -t apex-maven .
docker run -it --name=apex -v $JANSING_PROJECT_HOME:/home/apex/$JANSING_REPO_NAME -p 50070:50070 -p 8089:8088 apex-maven