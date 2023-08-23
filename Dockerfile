FROM openjdk:21-ea-17-oracle

WORKDIR /app

COPY rest/target/tinqin.zoobff.jar /app/tinqin.zoobff.jar

EXPOSE 8082

CMD ["java", "-jar", "tinqin-zoobff.jar"]