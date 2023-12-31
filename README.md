# TAXI CERVICE
# 🚀 Project description
A simple web-application that supports database work, authentication, registration, adding, updating, viewing and deleting items from DB and other CRUD operation, logging requests/responses to the application to the database. The project is a web application for taxi management that uses Java and JavaServer Pages (JSP) technology to create dynamic pages. Design patterns and segregation of duties are used to create a clean and easily extensible architecture. Taxi Service is a web application created to manage drivers, cars and manufacturers in a taxi company. The application allows you to add, update, delete drivers, cars and manufacturers, as well as assign drivers to cars.
# Features
- Adding new drivers, cars and manufacturers to the database.<br>
- Update information about drivers, cars and manufacturers.<br>
- Removing drivers, cars and manufacturers from the database.<br>
- Assignment of drivers to specific cars.<br>
- Logging of user actions<br>
# 🏗️ Project structure
- **src/main/java** - Java code sources.
   - **taxi.controller** - controllers process HTTP requests and interact with related services and models.<br>
  - **taxi.dao** - database access classes. The Data Access Object template is used to interact with the database. Interfaces and their implementations provide CRUD (create, read, update, delete) operations.<br>
The **DAO** is responsible for maintaining communication with the database, allowing the creation, reading, updating, and deletion of data.
  - **taxi.exception** - this class handles exceptions that may occur during query execution and displays an appropriate message to the user.<br>
  - **taxi.lib** - additional classes for control inversion and other general tasks.<br>
  - **taxi.model** - classes representing the data model. There are **Car, Driver,** and **Manufacturer** classes that represent Car, Driver, and Manufacturer objects. Relationships between models, such as drivers for each car, are defined.<br>
  - **taxi.service** - services for managing business logic. Interfaces and their implementations for managing drivers, cars and manufacturers. Services implement business process logic such as adding, deleting, retrieving a list, etc.<br>
  - **taxi.util** - helper classes and utilities are located that provide useful functions and simplify certain processes for your project, including working with the database and other common operations. The overall goal of the taxi.util package is to simplify and standardize work with common tasks and resources.<br>
  - **taxi.web.filter** - filters are located that process HTTP requests before they reach the controllers.

- **src/main/resources** - there are files for creating a database and configuring the logger.
    - **init_db.sql** instructions for creating a database
    - **log4j2.xml** logger configuration file

- **taxi.webapp** - web components such as filters and controllers. JSP templates generate pages with data coming from the backend. JSTL is used to display data and manage page structure.<br>
- **src/main/test/java** - a package of testing errors in the work of the project<br>
**The total coverage of methods and code is:**<br>

![img_1.png](img_1.png)

# 💡 Technologies used
- Java (v 11.0.17)<br>
- Servlet & JSP (v 4.0.1)<br>
- YSTL (Javaserver Pages Standard Tag Library v 4.0.1)<br>
- MySQL (v 8.0.28)<br>
- Tomcat (v 9.0.50)
- JUnit Jupiter test engine.(JUnit5)<br>
- Log4j logging framework (v 2.17.1)

# 📚 Instructions for  launching the project
<p style="margin-left: 50px;">1. Create a MySQL database named **taxi** and execute the SQL script to create the tables (**src/main/resources/init_db.sql**).
<p style="margin-left: 50px;">2. Download the project and import it into your workspace (IDE) as a **Maven project.**
<p style="margin-left: 50px;">3. In the file **src/main/java/taxi/util/ConnectionUtil.java**, specify the data to connect to your MySQL database (URL, username and password).
<p style="margin-left: 50px;">4. In the file **src/main/resources/log4j2.xml** in line 7 enter in parameters fileName=**"absolute_path_to_your_log_file**">
<p style="margin-left: 50px;">5. Start the application on your server (**Apache Tomcat**).
<p style="margin-left: 50px;">6. Open a browser and navigate to <a href="http://localhost:8080/">http://localhost:8080/</a> to interact with the application.

# 🔧 Other divisions
- Author: **Yurii Ivanov**<br>
- Contact information: <a href="mailto:ivanovyura76@gmail.com">ivanovyura76@gmail.com</a><br> 
- License: Copyright (c) 2023 by Yurii Ivanov. Taxi service is free and open-source software licensed under the MIT License.