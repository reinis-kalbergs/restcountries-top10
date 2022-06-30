package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.service.getcountriesservice.AbstractCountriesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountriesPatternServiceTest {

    @Mock
    AbstractCountriesService abstractCountriesService;

    @InjectMocks
    CountriesPatternService countriesPatternService;

    @Test
    void shouldMatchCorrectCountries() {
        Country spain = new Country("Spain", null, null, null, null);
        Country sweden = new Country("Sweden", null, null, null, null);
        Country malta = new Country("Malta", null, null, null, null);
        Country latvia = new Country("Latvia", null, null, null, null);
        Country[] countries = {spain, sweden, malta, latvia};
        String wildcard = "s*N";
        List<Country> expectedCountries = Arrays.asList(spain, sweden);

        Mockito.when(abstractCountriesService.getAllEuCountries()).thenReturn(countries);

        List<Country> matchedCountries = countriesPatternService.getAllCountriesWithPattern(wildcard);
        Assertions.assertIterableEquals(expectedCountries, matchedCountries);
    }
}
