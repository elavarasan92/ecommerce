FROM openjdk:11
MAINTAINER Elavarasan
ADD schema.sql /docker-entrypoint-initdb.d
COPY target/ecommerce.jar ecommerce.jar
ENTRYPOINT ["java","-jar","/ecommerce.jar"]