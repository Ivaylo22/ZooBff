FROM openjdk:17.0.2-oracle
WORKDIR /app
COPY target/*.jar /app/tinqin-zoobff.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "tinqin-zoobff.jar"]