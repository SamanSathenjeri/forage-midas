# Midas
Project repo for the JPMC Advanced Software Engineering Forage program

Core Spring Boot Dependencies
spring-boot-starter-data-jpa from org.springframework.boot
This dependency is your go-to for working with databases. JPA stands for Java Persistence API, which is a standard way to map Java objects to relational database tables. This starter brings in everything you need to easily connect to a database and perform operations like saving, finding, and updating data. It includes a library called Hibernate, which does the heavy lifting of translating your Java code into SQL commands.

spring-boot-starter-web from org.springframework.boot
This is the core dependency for building web applications and REST APIs. It includes everything necessary to create a web server, handle incoming HTTP requests, and send back responses. It also brings in a library called Tomcat, which is a popular web server. This starter lets you easily create endpoints, which are like specific URLs that your application listens to.

spring-boot-starter-test from org.springframework.boot
This dependency is for testing your application. It provides a comprehensive set of libraries and tools for writing tests, including JUnit, the de facto standard for unit testing in Java. This starter makes it easy to write tests that check if your code is working as expected.

Database and Messaging Dependencies
H2 from com.h2database
H2 is an in-memory database. This means it runs entirely in your computer's memory and doesn't write data to a file on your hard drive unless you configure it to. It's often used for development and testing because it's very fast, easy to set up, and automatically cleans up all the data when your application stops. You wouldn't typically use this in a production environment.

spring-kafka from org.springframework.kafka
This dependency is for connecting your application to Apache Kafka. Kafka is a high-performance, distributed streaming platform used for handling large amounts of data in real-time. This dependency provides a simple way to send and receive messages from Kafka topics.

Testing Dependencies
spring-kafka-test from org.springframework.kafka
Similar to the core test dependency, this one is specifically for testing applications that use Spring Kafka. It provides a test harness that allows you to easily simulate a Kafka environment in your tests, so you can test your message-producing and consuming logic without needing a real Kafka server running.

Kafka from org.testcontainers
This dependency is part of Testcontainers, a library that provides lightweight, throwaway instances of databases, message queues, and other services in Docker containers. For testing, this is a more robust alternative to in-memory databases or simulated environments because it spins up a real Kafka instance in a container. This ensures your tests run against an environment that's very close to your actual production environment, which helps catch bugs that might not appear in a simulated test.