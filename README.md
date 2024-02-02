# Developpez-une-application-full-stack-complete

This application is a social network for developer Beta. You can now sign up, log in, subscribe to themes and write articles for those themes. In addition, you have access to your profile to change your credentials, log out and unsubscribe.

Developpez-une-application-full-stack-complete is a full-stack application testing project utilizing essential dependencies like Spring Boot, MySQL, JJWT, Lombok, Maven, Angular

## ️ Settings

### Step 1 - Prerequistes :

Make sure the following softs are installed

- Angular CLI version 14.1.0.
- Java JDK 17
- Maven
- MySQL >= 8

### Step 2 - Database

- Start MySql
- Create the BDD by importing the SQL script located in ./resources/script.sql
- Add in your properties :
  - spring.datasource.username: (username)
  - spring.datasource.password: (password)
  - spring.datasource.url : (url of database)

By default they are two accounts:

- login: devuser1@example.com
- password: Passw0rd@

- login: devuser2@example.com
- password: Passw0rd@

### Step 3 - Spring Security

For use JWT and create token, add in your properties :
jwt.secret= (secret code)

## Run Locally

### Instructions

1.  Fork this repo
2.  Clone the repo onto your computer
3.  Open a terminal window in the cloned project
4.  Run the following commands:

**Back :**

1.Install dependencies :

```bash
mvn install
```

3.Start the development mode:

```bash
java -jar app.jar
```

**Front:**

1.Install dependencies :

```bash
npm install
```

3.Start application:

```bash
ng serve
```

## Test Api

Use Postman collection to test several routes.

```bash
├── ressources
│   └── postman
│       ├── rental.postman_collection.json
```

## Version

V 1.0.0
