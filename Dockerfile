FROM openjdk:11
MAINTAINER Elavarasan
COPY target/Ecommerce-0.0.1-SNAPSHOT.jar Ecommerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Ecommerce-0.0.1-SNAPSHOT.jar"]