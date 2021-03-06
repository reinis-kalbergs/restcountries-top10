package com.example.restcountriestop10.service.getcountriesservice;

import com.example.restcountriestop10.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;

@Service
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "json")
@Slf4j
public class CountriesJsonService extends AbstractCountriesService {

    private final String FULL_PATH = "./src/main/resources/data/EuCountries.json";
    private final ObjectMapper objectMapper;

    public CountriesJsonService(Environment environment, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(environment, restTemplate);
        this.objectMapper = objectMapper;
    }

    @Override
    public Country[] getAllEuCountries() {
        log.info("Retrieving data from json file");
        try {
            return objectMapper.readValue(new File(FULL_PATH), Country[].class);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
