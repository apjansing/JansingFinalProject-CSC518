Development Apache Apex Docker Image and Example MapReduce

## Abstract
Apache Apex is Hadoop YARN-native framework for building distributed applications and applies native streaming to the data processing pipeline [[3]](#WEISE). The Apex project was mainly been driven by the company DataTorrent. DataTorrent shut its doors back in May of 2018 [[2]](#WIKI) [[7]](#DATANAMI).

## Example
### Starting Docker Container
To start the `apex` docker container,
```
git clone git@github.com:apjansing/JansingFinalProject-CSC518.git
cd src/docker
./pull_and_start_dev_apex_docker.sh
```

### Running MapReduce
After the `apex` docker container is started,
```
cd cd src/MapReduce
mvn clean install
```

### Output
Once the Maven build finishes, you can see that the tests generated a `tmp` folder with an `output*` file. The `output*` file will have contents that look like,
```
{monstrous=1, allah=1, xlv=1, xxxvii=1, bag=1, dutch=1, xxiv=1, vii=3,...},
{... cheerless=1, monstrous=4, flinty=1, been=12, gaping=1, ...},
{... ticking=1, monstrous=1, been=64, mostly=2, ...},
{... account=5, monstrous=6, been=60, unquestion=1, ...}
```
Notice that the words _monstrous_, _been_, and other words repeatedly show up in separate jsons. That is as a result of how Apache Apex streams data and outputs the results of the DAG (Directed Acyclic Graph) object is saved after a time window.

## Resources
 * [Docker Hub](https://hub.docker.com/r/apjansing/apex-maven/)

## Bibliography
<a name="APEX">[1]</a>
Foundation, A. (2018). Apache Apex. [online] Apex.apache.org. Available at: https://apex.apache.org/ [Accessed 5 Nov. 2018].

<a name="WIKI">[2]</a>: En.wikipedia.org. (2018). Apache Apex. [online] Available at: https://en.wikipedia.org/wiki/Apache_Apex [Accessed 10 Nov. 2018].

<a name="WEISE">[3]</a>Weise, T. (2016). Architectual Comparison of Apache Apex and Spark Streaming. [online] Available at: https://www.slideshare.net/ApacheApex/architectual-comparison-of-apache-apex-and-spark-streaming [Accessed 10 Nov. 2018].

<a name="APEXrtd_dt">[4]</a>
Dt-docs.readthedocs.io. (2018). Beginner's Guide - DataTorrent Documentation. [online] Available at: https://dt-docs.readthedocs.io/en/latest/beginner/ [Accessed 10 Nov. 2018].

<a name="APEXrtd_apache">[5]</a>
Apex.apache.org. (2018). Apache Apex Documentation. [online] Available at: http://apex.apache.org/docs/apex/ [Accessed 10 Nov. 2018].

<a name="MALHAR">[6]</a>
Apex.apache.org. (2018). Apache Apex Malhar Documentation. [online] Available at: http://apex.apache.org/docs/malhar/ [Accessed 10 Nov. 2018].

<a name="DATANAMI">[7]</a> Leopold, G. (2018). DataTorrent, Stream Processing Startup, Folds. [online] Datanami. Available at: https://www.datanami.com/2018/05/08/datatorrent-stream-processing-startup-folds/ [Accessed 10 Nov. 2018].