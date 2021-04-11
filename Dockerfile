FROM java:11

EXPOSE 8080

ADD target/water-api-1.0.0.jar water-api-1.0.0.jar

ENTRYPOINT ["java", "-jar", "water-api-1.0.0.jar"]