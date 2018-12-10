FROM apacheapex/sandbox:3.7.0
MAINTAINER Alexander Jansing

USER root

RUN usermod -p '' apex
RUN echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

USER apex

ARG USER_HOME_DIR="/home/apex"
WORKDIR $USER_HOME_DIR

# Installing JDK8
RUN sudo apt-get -y update && sudo apt-get install -y \
		bzip2 \
		unzip \
		xz-utils

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

ENV JAVA_VERSION 8u111
ENV JAVA_DEBIAN_VERSION 8u111-b14-2~bpo8+1

RUN set -x \
	&& sudo apt-get update \
	&& sudo apt-get install -y \
		openjdk-8-jdk

# Installing Maven 3.6.0
ARG MAVEN_VERSION=3.6.0
ARG SHA=fae9c12b570c3ba18116a4e26ea524b29f7279c17cbaadc3326ca72927368924d9131d11b9e851b8dc9162228b6fdea955446be41207a5cfc61283dd8a561d2f
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p $USER_HOME_DIR/maven $USER_HOME_DIR/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C $USER_HOME_DIR/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz

ARG MAVEN_HOME="$USER_HOME_DIR/maven"
ENV PATH="$PATH:$MAVEN_HOME/bin"

ENV MAVEN_HOME $USER_HOME_DIR/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Installing Apex-Malhar version 4.0.0 for building project.
RUN curl -fsSL -o /tmp/mahlar-git.zip https://github.com/apache/apex-malhar/archive/master.zip \
    && unzip /tmp/mahlar-git.zip -d $USER_HOME_DIR \ 
    && rm -f /tmp/mahlar-git.zip

RUN echo $JAVA_HOME
RUN ls -lRa /usr/lib/jvm/java-8-openjdk-amd64/
RUN cd apex-malhar-master && mvn clean install -DskipTests

EXPOSE 50070 8088

ENTRYPOINT ["/app/init.sh"]