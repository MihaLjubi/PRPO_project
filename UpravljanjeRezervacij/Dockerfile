FROM adoptopenjdk
RUN mkdir /app
WORKDIR /app
ADD ./api/target/api-1.0-SNAPSHOT.jar /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "api-1.0-SNAPSHOT.jar"]