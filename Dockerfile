FROM openjdk:8-jre-alpine
VOLUME /tmp

ARG VERSION

ADD weixin-public-${VERSION}.jar app.jar

ADD config/ config

RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
