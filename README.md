# prototyps
Prototyps for the Masterthesis

## GraphlQL
Test can just be executed.

## Spring Config
Repository URL is to be configured in springconfigserver/src/main/resource/application.properties
The server expects a RabbitMQ present on the system. Either install one or use a docker containter.
If the settings(password, user, port) differ, they must as well be configure in the applications.properties.

## MySQL
Needs an installation of Mysql or a Docker container with MySQL. Configuration can be found in springmysql/src/main/resource/application.properties

## MongoDB
Needs an installation of MongoDB(Normal or Replica Set) or a Docker Container with MongoDB(See http://www.sohamkamani.com/blog/2016/06/30/docker-mongo-replica-set/ for a setup). Configuration can be found in springmongo/src/main/resource/application.properties
To see the effect with to different Software, checkout branch beforeDatabaseChanges and build the project springmongo with mvn clean package docker:build. Repeat the step for the master branch.
Both version can then be started with docker run like '' docker run -t 8090:8080 (-net network) -name containername akajimoru/spring-mongo:1.1-SNAPSHOT. Network is only needed if the above linked tutorial was made.


