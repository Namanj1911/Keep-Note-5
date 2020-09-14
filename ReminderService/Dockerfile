From openjdk:11-jre
WORKDIR usr/lib
ENV MONGO_DATABASE=reminddb
#ENV MONGO_USERNAME=root
#ENV MONGO_PASSWORD=root
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
COPY ./target/ReminderService-0.0.1-SNAPSHOT.jar /usr/lib/ReminderService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ReminderService-0.0.1-SNAPSHOT.jar"]