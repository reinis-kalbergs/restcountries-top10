package com.example.restcountriestop10.service.getcountriesservice;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.model.CountryInDatabase;
import com.example.restcountriestop10.repository.CountryRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "database")
public class CountriesDatabaseService extends AbstractCountriesService {

    private final CountryRepository countryRepository;

    //private final CurrencyRepository currencyRepository;
    //todo: add currency table
    public CountriesDatabaseService(Environment environment, RestTemplate restTemplate, CountryRepository countryRepository) {
        super(environment, restTemplate);
        this.countryRepository = countryRepository;
    }

    @Override
    public Country[] getAllEuCountries() {
        saveCountriesFromApi();
        return countryRepository.findAll().stream()
                .map(Country::new)
                .toArray(Country[]::new);
    }

    private void saveCountriesFromApi() {
        Country[] countries = getAllEuCountriesFromRest();
        List<CountryInDatabase> countriesToSave = Arrays.stream(countries)
                .map(CountryInDatabase::new)
                .toList();
        countryRepository.saveAll(countriesToSave);
    }
}
