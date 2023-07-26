FROM openjdk:11
MAINTAINER Elavarasan
COPY target/ecommerce.jar ecommerce.jar
ENTRYPOINT ["java","-jar","/ecommerce.jar"]