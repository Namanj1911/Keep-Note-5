FROM openjdk:11-jre
WORKDIR usr/lib
ENV MYSQL_DATABASE=keepnote
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=apppass
ENV MYSQL_HOST=localhost
COPY ./target/UserAuthentication-Service-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthentication-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "UserAuthentication-Service-0.0.1-SNAPSHOT.jar"]