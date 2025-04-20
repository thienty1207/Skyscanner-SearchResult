# Hoen Scanner

This is a simple Dropwizard application for searching hotels and rental cars by city.

## Requirements
- Java 21
- Maven

## Building the Application
To build the application, run:
```
mvn clean package
```

## Running the Application
To run the application, use:
```
java -jar target/Skyscanner-1.0-SNAPSHOT.jar server src/main/resources/config.yml
```

## Testing the Application
You can test the application using Postman or curl:

1. Send a POST request to `http://localhost:8080/search`
2. Set the Content-Type header to `application/json`
3. Use a request body like:
   ```json
   {"city": "petalborough"}
   ```

Example cities to try:
- petalborough
- rustburg
- shaleport