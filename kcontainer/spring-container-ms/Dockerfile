# Build stage - could use maven or our image
FROM maven:3.3-jdk-8 as builder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app

ARG POSTGRESQL_URL
ARG POSTGRESQL_USER
ARG POSTGRESQL_PWD
ARG KAFKA_BROKERS
ARG KAFKA_APIKEY
# postgresql ssl certicates
ARG POSTGRESQL_CA_PEM
# and event streams one
ARG ES_CA_PEM
ARG TRUSTSTORE_PWD
RUN bash -c " export POSTGRESQL_URL=${POSTGRESQL_URL} \
	&&  export POSTGRESQL_USER=${POSTGRESQL_USER}\
	&& export POSTGRESQL_PWD=${POSTGRESQL_PWD} \
	&& export KAFKA_BROKERS=${KAFKA_BROKERS} \
	&& export KAFKA_APIKEY=${KAFKA_APIKEY} \
	&& export ES_CA_PEM=\"${ES_CA_PEM}\" \
	&& export POSTGRESQL_CA_PEM=\"${POSTGRESQL_CA_PEM}\" \
	&& export JKS_LOCATION=\"/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/security/cacerts\" \
	&& export TRUSTSTORE_PWD=${TRUSTSTORE_PWD} \
	&& ./scripts/add_certificates.sh \
#GITSECURE REMEDIATION 
RUN  apt-get update && apt-get install -y --fix-missing git=1:2.11.0-3+deb9u5  subversion=1.9.5-1+deb9u4 \ 
     wget=1.18-5+deb9u3  openssl=1.1.0l-1~deb9u1 \ 
     e2fsprogs=1.43.4-2+deb9u1   


# Deploy stage
# Use jre small foot print image. See https://hub.docker.com/_/ibmjava/
FROM openjdk:8u181-jdk as runner
ENV APP_HOME=/app
COPY --from=builder /usr/src/app/target/SpringContainerMS-1.0-SNAPSHOT.jar $APP_HOME/app.jar
LABEL maintainer="IBM Java Engineering at IBM Cloud"

# Install Extra Packages
RUN apt1.0.9.8.5 update \ && apt-get install -y jq bash4.3-11+deb8u2 bc ca-certificates curl7.52.1-5+deb9u10 nodejs \ && update-ca-certificates \ && mkdir -p $APP_HOME/scripts   git1:2.11.0-3+deb9u5  dbus1.8.22-0+deb8u2  subversion1.9.5-1+deb9u4  wget1.18-5+deb9u3  openssl1.1.0l-1~deb9u1  systemd215-17+deb8u12  mercurial3.1.2-2+deb8u7  sensible-utils0.0.9+deb8u1  perl5.20.2-3+deb8u12  procps2:3.3.9-9+deb8u1  bzip21.0.6-7+deb8u1  libxml22.9.1+dfsg1-5+deb8u8  tar1.27.1-2+deb8u2  e2fsprogs1.43.4-2+deb9u1  bzr2.6.0+bzr6595-6+deb8u1  python2.72.7.9-2+deb8u5  unzip6.0-21+deb9u1  gnupg1.4.18-7+deb8u4  

WORKDIR $APP_HOME

COPY scripts/startup.sh startup.sh
COPY scripts/add_certificates.sh scripts/

EXPOSE 8080:8080
ENTRYPOINT ["./startup.sh"]
