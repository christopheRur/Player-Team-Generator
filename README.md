### Requirements

- 8.0 <= Java <= 14.0
- Spring boot as provide / compatible with the java version
- H2 database
- Maven latest

### Installation
- Clone the code from repository
- Run maven install command: `mvn install`
- To serve the api run the command: `mvn spring-boot:run`
- It will serve the api under localhost:3000
- To run the tests use the command: `mvn test`
- All command should be run from the root directory

> As a note before submit your code make sure that all of the dependencies are working well. You can run `mvn clean install` to check if all the maven dependencies are okay to avoid build failure.