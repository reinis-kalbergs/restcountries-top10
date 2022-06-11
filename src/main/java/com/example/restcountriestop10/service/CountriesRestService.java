package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "restapi")
public class CountriesRestService extends AllCountriesGetter {

    public CountriesRestService(Environment environment, RestTemplate restTemplate) {
        super(environment, restTemplate);
    }

    @Override
    Country[] getAllEuCountries() {
        return getAllEuCountriesFromRest();
    }
}
