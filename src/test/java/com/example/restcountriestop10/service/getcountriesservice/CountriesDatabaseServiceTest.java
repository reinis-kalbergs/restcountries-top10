package com.example.restcountriestop10.service.getcountriesservice;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.model.CountryInDatabase;
import com.example.restcountriestop10.model.Currency;
import com.example.restcountriestop10.repository.CountryRepository;
import com.example.restcountriestop10.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountriesDatabaseServiceTest {

    final Currency euro = new Currency("EUR", "Euro", "€");
    final Set<Currency> countryCurrencies = new HashSet<>(List.of(euro));
    final Country COUNTRY_1 = new Country("Netherlands", "Amsterdam", 17441139L, 41850, countryCurrencies);
    final Country COUNTRY_2 = new Country("Netherlands", "Amsterdam", 17441139L, 41850, countryCurrencies);
    @Mock
    Environment environment;
    @Mock
    RestTemplate restTemplate;
    @Mock
    CountryRepository countryRepository;
    @Mock
    CurrencyRepository currencyRepository;
    @InjectMocks
    CountriesDatabaseService countriesDatabaseService;
    @Captor
    ArgumentCaptor<List<CountryInDatabase>> countryCaptor;
    @Captor
    ArgumentCaptor<Set<Currency>> currencyCaptor;

    @Test
    void shouldReceiveData() {
        CountryInDatabase countryInDatabase1 = new CountryInDatabase(COUNTRY_1);
        CountryInDatabase countryInDatabase2 = new CountryInDatabase(COUNTRY_2);

        List<CountryInDatabase> countries = Arrays.asList(countryInDatabase1, countryInDatabase2);
        when(countryRepository.count()).thenReturn(2L);
        when(countryRepository.existsByLastUpdatedBefore(Mockito.any(LocalDateTime.class))).thenReturn(false);
        when(countryRepository.findAll()).thenReturn(countries);

        Country[] expectedResult = {COUNTRY_1, COUNTRY_2};
        Country[] result = countriesDatabaseService.getAllEuCountries();

        Assertions.assertIterableEquals(Arrays.asList(result), Arrays.asList(expectedResult));
    }

    @Test
    void shouldUpdateCountriesWhenDataIsStale() {
        Country[] countries = {COUNTRY_1, COUNTRY_2};
        when(countryRepository.count()).thenReturn(2L);
        when(countryRepository.existsByLastUpdatedBefore(Mockito.any(LocalDateTime.class))).thenReturn(true);
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(countries);

        CountryInDatabase countryInDatabase1 = new CountryInDatabase(COUNTRY_1);
        CountryInDatabase countryInDatabase2 = new CountryInDatabase(COUNTRY_2);
        List<CountryInDatabase> expectedResult = Arrays.asList(countryInDatabase1, countryInDatabase2);

        when(currencyRepository.saveAll(currencyCaptor.capture())).thenReturn(null);
        when(countryRepository.saveAll(countryCaptor.capture())).thenReturn(null);
        countriesDatabaseService.getAllEuCountries();

        List<CountryInDatabase> savedCountries = countryCaptor.getAllValues().get(0);

        Assertions.assertIterableEquals(savedCountries, expectedResult);
    }

    @Test
    void shouldUpdateCurrenciesWhenDataIsStale() {
        Country[] countries = {COUNTRY_1};
        when(countryRepository.count()).thenReturn(2L);
        when(countryRepository.existsByLastUpdatedBefore(Mockito.any(LocalDateTime.class))).thenReturn(true);
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(countries);

        when(currencyRepository.saveAll(currencyCaptor.capture())).thenReturn(null);
        when(countryRepository.saveAll(countryCaptor.capture())).thenReturn(null);
        countriesDatabaseService.getAllEuCountries();

        List<Set<Currency>> savedCurrencies = currencyCaptor.getAllValues();

        for (Set<Currency> currencySet : savedCurrencies) {
            Assertions.assertIterableEquals(currencySet, countryCurrencies);
        }
    }
}
