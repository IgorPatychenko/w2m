FROM openjdk:11

COPY target/w2m-0.0.1-SNAPSHOT.jar /w2m.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java","-jar","/w2m.jar"]