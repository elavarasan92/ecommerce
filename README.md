# Getting started ecommerce application

## Quick start

### Clone the repository

```
git clone https://github.com/elavarasan92/ecommerce.git
```

### Build the project

```
docker-compose build
```

The project includes a ``web`` service, running the Java code, and a ``db`` service, running a Mysql database.
See the ``docker-compose.yml`` file for details.

### Run the project

```
docker-compose up
````

Containers for both services will be launched. The project can be reached at http://localhost:8080.

## How to

### Run the local project on a different port

The container runs a Tomcat server listening on port 8080. The ``docker-compose.yml`` file is set up to
expose this port to the Docker host at port 8080, but you are free to change it as you wish - edit the ``ports`` directive:

```
services:
  web:
    [...]
    ports: 
      - 8080:8080
```

## Reference

This project template uses:

* Java 11