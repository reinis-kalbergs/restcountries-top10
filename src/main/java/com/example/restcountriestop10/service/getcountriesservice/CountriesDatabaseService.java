package com.example.restcountriestop10.service.getcountriesservice;

import com.example.restcountriestop10.model.Country;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "database")
public class CountriesDatabaseService extends AbstractCountriesService {

    public CountriesDatabaseService(Environment environment, RestTemplate restTemplate) {
        super(environment, restTemplate);
    }

    @Override
    public Country[] getAllEuCountries() {
        return new Country[0];
    }
}
