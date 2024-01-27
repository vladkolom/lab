FROM jenkins/jenkins:lts

USER root

RUN apt-get update -qq \
    && apt-get install -y wget unzip
