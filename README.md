This is a REST implementation of popular Northwind database that deals with Customers, Orders, Products and Employees. It contains a stripped down version of Northwind database with some sample data. The project can serve as a source to understand some of the components of spring such as REST, Secirity, JDBC or can act as a RESTfull backend for an fornend App providing sample data, paginated data, heirarchial data and HTTP errors 

### Technology Stack
Component         | Technology
---               | ---
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
Security          | Token Based (Spring Security and [JWT](https://github.com/auth0/java-jwt) )
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox) and [ReDoc](https://github.com/Rebilly/ReDoc)
REST Spec         | [Open API Standard](https://www.openapis.org/) 
In Memory DB      | H2 
Persistence       | JPA (Using Spring Data)
Server Build Tools| Maven(Java) or Gradle

## Folder Structure
```bash
PROJECT_FOLDER
│  README.md
│  pom.xml           
│  build.gradle
└──[src]      
│  └──[main]      
│     └──[java]      
│     └──[resources]
│        │  application.properties #contains springboot cofigurations
│        │  schema.sql  # Contains DB Script to create tables that executes during the App Startup          
│        │  data.sql    # Contains DB Script to Insert data that executes during the App Startup (after schema.sql)
│        └──[public]    # keep all html,css etc, resources that needs to be exposed to user without security
│
└──[target]              #Java build files, auto-created after running java build: mvn install
│  └──[classes]
│     └──[public]
│     └──[webui]         #webui folder is created by (maven/gradle) which copies webui/dist folder 
│                        #the application.properties file list webui as a resource folder that means files can be accesses http://localhost/<files_inside_webui> 
│
```

## Prerequisites
Ensure you have this installed before proceeding further
- Java 8
- Maven 3.3.9+ or Gradle 3.3+


## In Memory DB (H2)
I have included an in-memory database for the application. Database schema and sample data for the app is created everytime the app starts, and gets destroyed after the app stops, so the changes made to to the database are persistent only as long as the app is running
<br/>
Creation of database schema and data are done using sql scripts that Springs runs automatically. 
To modify the database schema or the data you can modify [schema.sql](./src/main/resources/schema.sql) and [data.sql](./src/main/resources/data.sql) which can be found at `/src/main/resources`


## Spring security
Security is **disabled** by default, to enable, you must uncomment [this line](./src/main/java/com/app/config/SecurityConfig.java#L15) in `src/main/java/com/config/SecurityConfig.java`<br/>
Once security is enabled, none of the REST API will be accessesble directly.

to test security access `http://localhost:9119/version` API and you should get a forbidden/Access denied error. 
In order to access these secured API you must first obtain a token. Tokens can be obtained by passing a valid userid/password

userid and password are stored in H2 database. To add/remove users, modify the [data.sql](./src/main/resources/data.sql#L7)
couple of valid users and their passwords are `demo\demo` and `admin\admin`
<br/>

To get a token call `POST /session` API with a valid userid and password.
for example you may you can use the folliwing curl command to get a token 
```
curl -X POST --header 'Content-Type: application/json' -d '{ "username":"demo", "password":"demo" }' 'http://localhost:9119/session'
```
the above curl command will return you a token, which should be in the format of `xxx.xxx.xxx`. This is a JSON web token format. 
You can decode and validate this token at [jwt.io wesite](https://jwt.io/). Just paste the token there and decode the information.
to validate the token you should provide the secret key which is `mrin` that i am using in this app.
<br/>
after receiving this token you must provide the token in the request-header of every API request. For instance try the `GET /version` api using the below 
curl command (replace xxx.xxx.xxx with the token that you received in above command) and you should be able to access the API.
```
curl -X GET --header 'Accept: application/json' --header 'Authorization: xxx.xxx.xxx' 'http://localhost:9119/version'
``` 

### Install Backend (SpringBoot Java)
```bash
# Gradle Build : Navigate to the root folder where build.gradle is present 
gradle build

#OR

# Maven Build : Navigate to the root folder where pom.xml is present 
mvn clean install
```

### Start the API and WebUI server
```bash
# Start the server (9119)
# port and other configurations for API servere is in [./src/main/resources/application.properties](/src/main/resources/application.properties) file

# If you build with gradle jar location will be 
java -jar ./build/libs/app-1.0.0.jar

# If you build with maven jar location will be 
java -jar ./target/app-1.0.0.jar
```

### Accessing Application
Cpmponent         | URL                                      | Credentials
---               | ---                                      | ---
H2 Database       |  http://localhost:9119/h2-console        |  Driver:`org.h2.Driver` <br/> JDBC URL:`jdbc:h2:mem:demo` <br/> User Name:`sa`
Swagger (API Ref) |  http://localhost:9119/swagger-ui.html   |
Redoc (API Ref)   |  http://localhost:9119/redoc/index.html  |


**To get an authentication token** 
```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"username": "demo", "password": "demo" }' 'http://localhost:9119/session'
```
or POST the username and password to http://localhost:9119/session


after you get the authentication token you must provide this in the header for all the protected urls 

```bash
curl -X GET --header 'Accept: application/json' --header 'Authorization: [replace this with token ]' 'http://localhost:9119/version'
```

### Screenshots
#### API
![API](/screenshots/api.png?raw=true)
---
####Database ER Diagram
![ER DIagram](/screenshots/ER_Model.png?raw=true)
