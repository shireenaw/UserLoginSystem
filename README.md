This application is created using Spring Security, Springboot framework, Java 17 and JPA entity data model.

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
        - Allowing unauthenticated GET-access to /login, /admin/* and /users/*.
        - Set custom login page and redirect the page based on roles
        - Handle unathorized page
2. CustomAuthenticationSuccessHandler
    - This class is used to redirect to different page based on roles after login successfully


There are 3 API Controllers. APIs
1. FilmController - This controller contains REST APIs regarding film inventory
    - user can find APIs to create new film, update film information or find film by name or the film id
    - the APIs are mainly being used for admin to maintain the Film Inventory.
2. UsersController - This controller contains REST APIs regarding user data
    - user can find APIs to create new user, update user, delete user or find user by mobile number or name.
    - this APIs are mainly being used for admin to register user
3. UserFilmController - This controller contains REST APIs regarding userfilm (rental)
    - user can use the APIs to create new user film , update , find and delete user film
    - getTotalRent() api is for the user to calculate the total rental of a list of films
    - checkLateCharges() api is for the user to calculate the surcharge by passing the list of film ids
    - returnFilms() api will update the data if there is late charges and the status of the user film (Rent/Returned/Late Returned)

Model
1. There are three entity model
    - Users (users entity table)

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
