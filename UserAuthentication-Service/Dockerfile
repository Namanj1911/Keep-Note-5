FROM openjdk:11-jre
WORKDIR usr/lib
ENV MYSQL_DATABASE=userDb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_HOST=localhost
COPY ./target/UserAuthentication-Service-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthentication-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "UserAuthentication-Service-0.0.1-SNAPSHOT.jar"]