package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public abstract class AbstractCountriesService {

    private final Environment environment;
    private final RestTemplate restTemplate;

    abstract Country[] getAllEuCountries();

    public Country[] getAllEuCountriesFromRest() {
        try {
            return retrieveEuCountries();
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private Country[] retrieveEuCountries() {
        final String URL_EU_COUNTRIES = environment.getProperty("restcountries.v2.url") + "/regionalbloc/eu";
        return restTemplate.getForObject(URL_EU_COUNTRIES, Country[].class);
    }
}
