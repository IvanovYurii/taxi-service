![img.png](img.png)
# Project description
A simple web-application that supports database work, authentication, registration, adding, updating, viewing and deleting items from DB and other CRUD operation, logging requests/responses to the application to the database. The project is a web application for taxi management that uses Java and JavaServer Pages (JSP) technology to create dynamic pages. Design patterns and segregation of duties are used to create a clean and easily extensible architecture. Taxi Service is a web application created to manage drivers, cars and manufacturers in a taxi company. The application allows you to add, update, delete drivers, cars and manufacturers, as well as assign drivers to cars.
# Features
• Adding new drivers, cars and manufacturers to the database.<br>
• Update information about drivers, cars and manufacturers.<br>
• Removing drivers, cars and manufacturers from the database.<br>
• Assignment of drivers to specific cars.<br>
• Logging of user actions<br>
# Project structure
• <b>src/main/java</b> - Java code sources.
<p style="margin-left: 50px;">
• <b>taxi.controller</b> - controllers process HTTP requests and interact with related services and models.<br>
<p style="margin-left: 80px;"><b>1. indexController:</b> This controller handles the main page of the application. It displays a list of links to go to different sections of the application.
<p style="margin-left: 80px;"><b>2. DriverController:</b> This controller is responsible for driver-related operations such as displaying the list of drivers, adding a new driver, and removing a driver.
<p style="margin-left: 80px;"><b>3. CarController:</b> This controller is responsible for operations related to cars, such as displaying a list of cars, adding a new car, adding a driver to a car, and removing a car.
<p style="margin-left: 80px;"><b>4. ManufacturerController:</b> This controller is responsible for operations related to manufacturers, such as displaying the list of manufacturers, adding a new manufacturer and removing a manufacturer.
<p style="margin-left: 80px;"><b>5. LoginController:</b> This controller is responsible for user authentication. It processes the login page, validates the input and allows users to log in.
<p style="margin-left: 80px;"><b>6. LogoutController:</b> This controller is responsible for terminating the user application session. It ends the session of the authorized user and redirects to the authentication page
<p style="margin-left: 50px;">• <b>taxi.dao</b> - database access classes. The Data Access Object template is used to interact with the database. Interfaces and their implementations provide CRUD (create, read, update, delete) operations.<br>
The <b>DAO</b> is responsible for maintaining communication with the database, allowing the creation, reading, updating, and deletion of data.
<p style="margin-left: 80px;"><b>1. CarDaoImpl:</b> implemented methods for working with cars data
<p style="margin-left: 80px;"><b>2. DriverDaoImpl:</b> implemented methods for working with drivers data
<p style="margin-left: 80px;"><b>3. ManufacturerDaoImpl</b> - implemented methods for working with manufacturesr data.
<p style="margin-left: 50px;">• <b>taxi.exception</b> - this class handles exceptions that may occur during query execution and displays an appropriate message to the user.<br>
<p style="margin-left: 50px;">• <b>taxi.lib</b> - additional classes for control inversion and other general tasks.<br>
<p style="margin-left: 80px;"><b>1. Injector:</b> This class implements the inversion of control mechanism and provides access to dependencies. You can use it to instantiate classes that you inject into other application components.
<p style="margin-left: 50px;">• <b>taxi.model</b> - classes representing the data model. There are <b>Car, Driver,</b> and <b>Manufacturer</b> classes that represent Car, Driver, and Manufacturer objects. Relationships between models, such as drivers for each car, are defined.<br>
<p style="margin-left: 80px;"><b>1. Driver:</b> This class represents a driver and has fields that display driver characteristics such as ID, name, license number, login and password.
<p style="margin-left: 80px;"><b>2. Car:</b> This class represents a car and has fields describing the ID, model of the car, the relationship to the manufacturer and the relationship to the drivers.
<p style="margin-left: 80px;"><b>3. Manufacturer:</b> This class represents the car manufacturer and has fields describing the manufacturer's name and country.</p>
<p style="margin-left: 50px;">• <b>taxi.service</b> - services for managing business logic. Interfaces and their implementations for managing drivers, cars and manufacturers. Services implement business process logic such as adding, deleting, retrieving a list, etc.<br>
<p style="margin-left: 80px;"><b>1. DriverService:</b> This service has methods for working with drivers, such as adding a new driver, getting a list of drivers, deleting a driver, and other operations related to drivers.
<p style="margin-left: 80px;"><b>2. CarService:</b> This service has methods for working with cars, such as adding a new car, getting a list of cars, adding a driver to a car, deleting a car, and other operations related to cars.
<p style="margin-left: 80px;"><b>3. ManufacturerService:</b> This service has methods for working with manufacturers, such as adding a new manufacturer, getting a list of manufacturers, removing a manufacturer, and other operations related to manufacturers.
<p style="margin-left: 50px;">• <b>taxi.util</b> - helper classes and utilities are located that provide useful functions and simplify certain processes for your project, including working with the database and other common operations. The overall goal of the taxi.util package is to simplify and standardize work with common tasks and resources.<br>
<p style="margin-left: 80px;">1. <b>ConnectionUtil:</b> This class provides a connection to a database using JDBC (Java Database Connectivity) and provides methods to obtain a connection and perform database queries.
<p style="margin-left: 110px;"><b>• getConnection():</b> this method creates and returns a connection to a <b>MySQL</b> database. For correct operation, you must set the corresponding values for <b>URL, USERNAME</b> and <b>PASSWORD</b> constants in this class to values corresponding to your <b>MySQL server</b> settings.
<p style="margin-left: 50px;">• <b>taxi.web.filter</b> - filters are located that process HTTP requests before they reach the controllers.
<p style="margin-left: 80px;"><b>1. AuthenticationFilter:</b> this filter verifies user authentication before processing HTTP requests. It redirects unauthenticated users to the login page.<br>
</p>
• <b>src/main/resources</b> - there are files for creating a database and configuring the logger.
<p style="margin-left: 50px;">• <b>init_db.sql</b> instructions for creating a database
<p style="margin-left: 50px;">• <b>log4j2.xml</b> logger configuration file
</p>
• <b>taxi.webapp</b> - web components such as filters and controllers. JSP templates generate pages with data coming from the backend. JSTL is used to display data and manage page structure.<br>
<p style="margin-left: 50px;">The <b>taxi.webapp.WEB-INF.view</b> package contains JSP files for displaying web pages.
<p style="margin-left: 80px;"><b>1. add_car.jsp:</b> JSP file for adding a new car.
<p style="margin-left: 80px;"><b>2. add_driver.jsp:</b> JSP file for adding a new driver.
<p style="margin-left: 80px;"><b>3. add_manufacturer.jsp:</b> JSP file for adding a new manufacturer.
<p style="margin-left: 80px;"><b>4. add_driver_to_car.jsp:</b> JSP file for adding a driver to a car.
<p style="margin-left: 80px;"><b>5. all_cars.jsp: JSP</b> file for displaying the list of all cars.
<p style="margin-left: 80px;"><b>6. all_drivers.jsp:</b> JSP file for displaying the list of all drivers.
<p style="margin-left: 80px;"><b>7. all_manufacturers.jsp:</b> JSP file for displaying the list of all manufacturers.
<p style="margin-left: 80px;"><b>8. login.jsp:</b> JSP file for displaying the login page.
<p style="margin-left: 80px;"><b>9. main.jsp:</b> JSP file to display the main page with links to different sections.
</p>
• <b>src/main/test/java</b> - a package of testing errors in the work of the project
<p style="margin-left: 50px;"><b>1. AuthenticationServiceImplTest</b> performance testing and error detection during user authentication
<p style="margin-left: 50px;"><b>2. CarServiceImplTest</b> testing operation and detecting errors when working with the Car class
<p style="margin-left: 50px;"><b>3. DriverServiceImplTest</b> testing operation and detecting errors when working with the Driver class
<p style="margin-left: 50px;"><b>4. ManufacturerServiceImplTest</b> testing the work and detecting errors when working with the Manufacturer class
</p>
<b>The total coverage of methods and code is:</b><br>

![img_1.png](img_1.png)

# Technologies used
• Java<br>
• Servlets<br>
• ISP (Javaserver Pages)<br>
• Thinking<br>
• YDBTS<br>
• YSTL (Javaserver Pages Standard Tag Library)<br>
• JUnit Jupiter test engine.<br>
• Log4j logging framework

# Instructions for launching the project
<p style="margin-left: 50px;">1. Create a MySQL database named <b>taxi</b> and execute the SQL script to create the tables (<b>src/main/resources/init_db.sql</b>).
<p style="margin-left: 50px;">2. Download the project and import it into your workspace (IDE) as a <b>Maven project.</b>
<p style="margin-left: 50px;">3. In the file <b>src/main/java/taxi/util/ConnectionUtil.java</b>, specify the data to connect to your MySQL database (URL, username and password).
<p style="margin-left: 50px;">4. In the file <b>src/main/resources/log4j2.xml</b> in line 7 enter in parameters fileName=<b>"absolute_path_to_your_log_file</b>">
<p style="margin-left: 50px;">5. Start the application on your server (<b>Apache Tomcat</b>).
<p style="margin-left: 50px;">6. Open a browser and navigate to <a href="http://localhost:8080/">http://localhost:8080/</a> to interact with the application.

# Other divisions
• Author: <b>Yurii Ivanov</b><br>
• Contact information: <a href="mailto:ivanovyura76@gmail.com">ivanovyura76@gmail.com</a><br> 
• License: Copyright (c) 2023 by Yurii Ivanov. Taxi service is free and open-source software licensed under the MIT License.