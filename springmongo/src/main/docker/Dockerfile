FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ENV DB_HOST mongodb
ENV DB_NAME meon
ADD spring-mongo-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 8080