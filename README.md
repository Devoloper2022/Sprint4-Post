# My Blog 

## Technologies:
- Java 21
- Spring Framework 6 (WebMVC, Spring Test)
- Thymeleaf 3
- Maven
- Apache Tomcat 10.1+
- H2 Database (for tests)
- JUnit 5 + Mockito

## Project Structure
- MVC layered architecture (`Controller`, `Service`, `DAO`)
- Manual configuration (`web.xml`, `DispatcherServlet`, etc.)
- WAR packaging for servlet container deployment
- Image upload support (`MultipartFile`)
- Full CRUD for blog posts, with tags, likes, comments

## Features
- Homepage and post list
- Full CRUD: create, edit, delete blog posts
- Add/edit/delete comments
- Like/dislike system
- Tag support
- Pagination and search
- Image upload

## Build WAR
- Use Maven to compile the project and generate a WAR file:
  ``bash mvn clean package``
- The WAR file will be located at:
  ``target/post.war``

## Deploy to Tomcat
- Install **Apache Tomcat 10.1 or higher**
-  Copy the WAR file to Tomcat's deployment directory:
   ``bash cp target/spring-web-myblog.war /path/to/tomcat/webapps/``
- Start Tomcat:
  ``bash cd /path/to/tomcat/bin./startup.sh``
- Open the app in your browser:
  ``http://localhost:8080/post/blog``
- add path dir to storage.path in `resource/application.properties`.

## Running Tests
This project includes unit and integration tests for:
- Service layer logic
- Run all tests using:
  ``bash mvn test``

## Usage
- Click “Add Post” to create a new blog entry
- Edit or delete posts and comments via the UI
- Like/dislike posts
- Use the search bar and pagination controls