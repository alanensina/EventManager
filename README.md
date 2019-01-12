# EventManager
A simple CRUD, using Spring Boot 2.1.1, mySQL, Hibernate and Thymeleaf. This CRUD is in charge of manage an event list.  
This is part of a Spring Boot course followed in https://goo.gl/3d7DDK , all rights reserved to @MichelliBrito
<p>Click on the image below to see the application running:

[![](http://img.youtube.com/vi/nyYWsr0LVtQ/0.jpg)](http://www.youtube.com/watch?v=nyYWsr0LVtQ "")

To run this application, you need to have installed mySQL database and Java8.

<b>1)</b> In terminal, access your mySQL database:
<p><b>mysql -h localhost -u root -p</b></p>

<b>2)</b> Create a database called eventosapp:
<p><b>create database eventosapp;</b></p>

<b>3)</b> Before to open the project in your IDE get int the root folder of the application and use the following command and wait to update the dependencies:
<p><b>mvn clean install</b></p>

<b>4)</b> Open the project in your IDE (Eclipse, IntelliJ)

<b>5)</b> Access the class DataConfigurationMySQL:
<p><b>src/main/java/com/eventoapp/connection/DataConfigurationMySQL.java</b></p>

<b>6)</b> Configure the user name and password of your database (lines 23 and 24):
<p><i>dataSource.setUsername("root"); // put your login of mySQL database
<p>dataSource.setPassword("123"); // put your password of mySQL database</i>

<b>7)</b> Run the application

<b>8)</b> After initialized, access:
<p><b>http://localhost:8080</b></p>


Thank you!
