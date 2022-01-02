FROM adoptopenjdk
RUN mkdir /app
WORKDIR /app
ADD ./api/target/api-RELEASE-0.4.jar /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-RELEASE-0.4.jar"]