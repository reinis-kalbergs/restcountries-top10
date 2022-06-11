# Countries top 10

REST API to get the top 10 eu countries by their population, area pr population density.
Using https://restcountries.com/v2

## Running

```console
mvnw spring-boot:run
```

## The application can be run in 2 modes:

- json - Application on startup will create a EuCountries.json file in resource folder. And on each request will use
  info from this file to expose information.

- restapi - Application on each request will in turn make a get request to https://restcountries.com/v2.

With the default settings application will start with restapi.

To start the API in json mode, you must set the following settings in application.properties to:

```
top10countries.retrieve-from=json
```

## Available endpoints

```
localhost:8080/top10/eu/population [GET]
```

```
localhost:8080/top10/eu/area [GET]
```

```
localhost:8080/top10/eu/population-density [GET]
```
