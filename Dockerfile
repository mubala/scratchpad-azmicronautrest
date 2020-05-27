FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY build/libs/*-all.jar azcosmos-ex-0.1-all.jar
CMD java ${JAVA_OPTS} -jar azcosmos-ex-0.1-all.jar