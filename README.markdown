# Goal

The goal of webapp-base is to provide a base for a Java Spring web
application, so you can worry about the application code instead of
setup and configuration.

# Features

* Working, runnable minimal web application
* Spring setup with Java Config
* Internationalization-ready with message sources
* User management with Spring Security, including login/logout and
  "remember me"
* Passwords are salted and encoded with SHA-256, using the database
  primary key as the salt (to obtain a primary key, the user is first
  saved with an unencrypted dummy password).

# Usage

1. Generate a self-signed certificate named "selfsigned.jks" in the
application root directory with the command `keytool -genkey -keyalg
RSA -alias selfsigned -keystore selfsigned.jks`.  Let the key password
be the same as the keystore password.  
2. Edit `keystorePass` property in `tomcat7-plugin` in `pom.xml` to
reflect the keystore password you just provided.  It defaults to
artifact id.
3. Edit the file `src/main/resources/db/migration/V2__Add_admin_user.sql`, and
change the admin user, email and password (default is `admin`).  The
password must be postfixed with `{1}` (the salt) and hashed.  Use the
following command: `echo -n <password>{1} | sha256sum`.
4. Edit `src/main/webapp/resources/js/page.js`, and change
`pathPrefix` to your webapp name.
5. Run the application using `mvn clean install tomcat7:run`.
6. Access the application via
`https://localhost:8443/<artifactId>`. You will get a certificate
warning, which you can ignore (or simply store a permanent security
exception).

# Libraries 

Webapp-base uses the following libraries (most important mentioned):

## Java (backend)

* Spring (dependency injection and transaction management)
* Spring MVC (web framework)
* Spring Security (web app security)
* JSP and Apache Tiles (server-side HTML generation)
* SLF4J and Logback (logging)
* Lombok (avoiding unnecessary boilerplate code, such as setters and getters)
* Hibernate (O/R mapping)
* H2 database (embedded SQL database)
* Flyway (database migrations)
* Hibernate Validator (bean validation, both in repositories and controllers)

## Javascript and CSS (frontend)

* Bootstrap (full-blown frontend framework using HTML, CSS and Javascript)
* Font Awesome (fonts that play nice with Bootstrap)
* Require.js (Javascript module loader)
* JQuery (Javascript Swiss knife)
