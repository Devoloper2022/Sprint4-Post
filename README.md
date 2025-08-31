# My Blog 

## Technologies:
- Java 21
- Spring Framework 6 (Spring Boot)
- Thymeleaf 3
- Gradle
- Apache Tomcat 10.1+
- H2 Database (for tests)
- JUnit 5 + Mockito

## Project Structure
- MVC layered architecture (`Controller`, `Service`, `DAO`)
- Configuration (`Spring Boot`, etc.)
- JAR packaging for servlet container deployment
- Image upload support (`MultipartFile`)
- Full CRUD for blog posts, with tags, likes, comments

## Features
- Homepage  post list
- Full CRUD: create, edit, delete blog posts
- Add/edit/delete comments
- Like/dislike system
- Tag support
- Image upload

## Build JAR
- Use Gradle to compile the project and generate a JAR file:
  ``bash ./gradlew clean build ``
- The JAR file will be located at:
  ``build/libs/Post-0.0.1-SNAPSHOT.jar``

## Deploy to Tomcat
- Start Tomcat:
  ``bash  java  -jar Post-0.0.1-SNAPSHOT.jar``
- Open the app in your browser:
  ``http://localhost:9091/``
- add path dir to storage.path in `resource/application.properties`.

## Running Tests
This project includes unit and integration tests for:
- Service layer logic
- Run all tests using:
  ``bash ./gradlew test``

## Usage
- Click “Add Post” to create a new blog entry
- Edit or delete posts and comments via the UI
- Like/dislike posts
- Use the search bar and pagination controls