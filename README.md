[![Build Status](https://travis-ci.org/andrey4623/spring-security-sample.svg?branch=master)](https://travis-ci.org/andrey4623/spring-security-sample)

# Spring Security Sample

This is just a simple project that shows how to use Spring MVC + Spring Security + Hibernate.

Please use the following script to create a MySQL database first:

```sh
CREATE  TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

INSERT INTO users (login, name) VALUES ('andrey4623', 'Andrey K');
```

## Requirements

- Java 1.8 or newer
- MySQL 5 or newer

## Building

The project requires Java 1.8 and Maven 3.3.9.

```sh
$ mvn clean install
```

## Deployment

You can put a servlet from /target folder to any web server you want to use. Or, just run

```sh
$ mvn jetty:run -Djetty.port=8080
```

from the root dir.

Use the login andrey4623 and password 123456 to log in.

## License

MIT
