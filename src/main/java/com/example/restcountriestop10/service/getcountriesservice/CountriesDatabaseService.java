package com.example.restcountriestop10.service.getcountriesservice;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.model.CountryInDatabase;
import com.example.restcountriestop10.repository.CountryRepository;
import com.example.restcountriestop10.repository.CurrencyRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "database")
public class CountriesDatabaseService extends AbstractCountriesService {

    private final CountryRepository countryRepository;

    private final CurrencyRepository currencyRepository;

    public CountriesDatabaseService(Environment environment, RestTemplate restTemplate,
                                    CountryRepository countryRepository, CurrencyRepository currencyRepository) {
        super(environment, restTemplate);
        this.countryRepository = countryRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Country[] getAllEuCountries() {
        if (dataIsStale()) {
            saveCountriesFromApi();
        }
        return countryRepository.findAll()
                .stream()
                .map(Country::new)
                .toArray(Country[]::new);
    }

    private boolean dataIsStale() {
        if (countryRepository.count() < 1) {
            return true;
        }
        LocalDateTime dateTimeADayAgo = LocalDateTime.now().minusDays(1);
        return countryRepository.existsByLastUpdatedBefore(dateTimeADayAgo);
    }

    private void saveCountriesFromApi() {
        Country[] countries = getAllEuCountriesFromRest();
        for (Country country : countries) {
            currencyRepository.saveAll(country.getCurrencies());
        }
        List<CountryInDatabase> countriesToSave = Arrays.stream(countries)
                .map(CountryInDatabase::new)
                .toList();
        countryRepository.saveAll(countriesToSave);
    }
}
