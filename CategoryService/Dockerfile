From openjdk:11-jre
WORKDIR usr/lib
ENV MONGO_DATABASE=keepnote
#ENV MONGO_USERNAME=root
#ENV MONGO_PASSWORD=root
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
COPY ./target/CategoryService-0.0.1-SNAPSHOT.jar /usr/lib/CategoryService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "CategoryService-0.0.1-SNAPSHOT.jar"]