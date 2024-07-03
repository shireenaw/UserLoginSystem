This application is created using Spring Security, Springboot framework, Spring Web, Java 17, Thymeleaf and JPA entity data model.

This application will seed the in memory database. This application contains both source code
and test cases for the service layer. In order to run the system,
1. Start the application
   the system will create the tables based on schema.sql and insert test data into the table in memory based on data.sql below.
2. There is two test data is being created : 
   - Admin (username:admin.test@gmail.com, password:admin)
   - User (username:user.test@gmail.com, password:user)
3. If login with Admin account, you will be directed to admin dashboard page which will be able to create new user, view all users, view specific user and delete user.
4. If login with User account, you will be directed to user homepage which will only able to see the user details.

SQL Database

users table Columns
user_id
first_name
last_name
mobile_num
email (unique)
password
role

Roles
A user can only be login as ADMIN or USER only.

Configuration
1. UserSecurityConfig
    - This is the configuration file of the application for Spring Security.
    - passwordEncoder() will configure bcrypt with 12 rounds as password hash algorithm. You can also generate a hash of a given password on https://bcrypt-generator.com/.
      - securityFilterChain(HttpSecurity) configures:
        - CSRF is disabled for easier access on this learning project.
        - Default HTTP Basic as authentication method.
        - Allowing unauthenticated access to the h2 console.
        - Set custom login page and redirect the page based on roles
        - Handle unauthorized page
2. CustomAuthenticationSuccessHandler
    - This class is used to redirect to different page based on roles after login successfully


There are 3 Controllers. APIs
1. AdminController - This controller contains views for admin dashboard page, create user page and view user details page.
2. UserController - This controller contains views for user home page only.
3. HomeController - This controller contains views for login page and user denied page.

Model
1. There are one entity, one dto and one mapper
    - Users (users entity table)
    - UsersDTO (user object)
    - UsersMapper (Mapping from UsersDTO to Users and vice versa)

Service and Repository
1. UserService and IUserService
   - This service contains of several methods to retrieve user, get all user or delete user.
2. UserRepository
   - This repository extends JPA Repository to retrieve data or submit data  to/ from Users table.

Junit test
1. The unit testing is on service level only It is to show the way to test for each services
2. Mockito is being used to mock the repository and services for testing

Application.properties
- it stores the configuration of in memory database set up
