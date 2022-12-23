FROM openjdk:11
WORKDIR . .
COPY target/accreditation_api-0.0.1-SNAPSHOT.jar accreditation_api.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","accreditation_api.jar"]