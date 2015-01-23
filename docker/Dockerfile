#Set Base Image
FROM debian:sid

# Author
MAINTAINER Florian Genaudet

# Update sources
RUN apt-get update

# auto validate license
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections

# update repos
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN apt-get update

# install java 8
RUN apt-get install oracle-java8-installer -y
RUN apt-get clean
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

#Install maven & git & java 8
RUN apt-get -y install git-core
RUN apt-get -y install maven

# Install mongodb
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv 7F0CEB10
RUN echo 'deb http://downloads-distro.mongodb.org/repo/debian-sysvinit dist 10gen' | tee /etc/apt/sources.list.d/mongodb.list
RUN apt-get update
RUN apt-get install -y mongodb-org
RUN mkdir -p /data/db

# install npm & bower
RUN apt-get -y install curl
RUN curl -sL https://deb.nodesource.com/setup | bash -
RUN apt-get install -y nodejs
RUN npm install -g bower

# Clone REPO
RUN git clone https://github.com/floDroidCoder/jug-quickies /home/ugquickies

# Expose the default port
EXPOSE 8080

# Compile and run app
WORKDIR /home/ugquickies/backend 
RUN mvn compile exec:exec install -DskipTests
CMD service mongod start && mvn exec:java

