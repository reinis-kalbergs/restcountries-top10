package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.model.CountryWithPopDensity;
import com.example.restcountriestop10.service.getcountriesservice.AbstractCountriesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MainCountriesServiceTest {

    final Country COUNTRY_1 = new Country("Gibraltar", "Gibraltar", 33691L, 6, null);
    final Country COUNTRY_2 = new Country("Malta", "Valletta", 525285L, 316, null);
    final Country COUNTRY_3 = new Country("Netherlands", "Amsterdam", 17441139L, 41850, null);
    final Country COUNTRY_4 = new Country("Belgium", "Brussels", 11555997L, 30528, null);
    final Country COUNTRY_5 = new Country("Luxembourg", "Luxembourg", 632275L, 2586, null);
    final Country COUNTRY_6 = new Country("Germany", "Berlin", 83240525L, 357114, null);
    final Country COUNTRY_7 = new Country("Italy", "Rome", 59554023L, 301336, null);
    final Country COUNTRY_8 = new Country("Isle of Man", "Douglas", 85032L, 572, null);
    final Country COUNTRY_9 = new Country("Czech Republic", "Prague", 10698896L, 78865, null);
    final Country COUNTRY_10 = new Country("Denmark", "Copenhagen", 5831404L, 43094, null);
    final Country COUNTRY_11 = new Country("France", "Paris", 67391582L, 640679, null);
    final Country COUNTRY_12 = new Country("Bulgaria", "Sofia", 6927288L, 110879, null);
    final Country[] TEST_COUNTRIES = {
            COUNTRY_1,
            COUNTRY_2,
            COUNTRY_3,
            COUNTRY_4,
            COUNTRY_5,
            COUNTRY_6,
            COUNTRY_7,
            COUNTRY_8,
            COUNTRY_9,
            COUNTRY_10,
            COUNTRY_11,
            COUNTRY_12
    };
    @Mock
    AbstractCountriesService abstractCountriesService;
    @InjectMocks
    MainCountriesService mainCountriesService;

    @Test
    void shouldGetCountriesByLargestPopulation() {
        CountryWithPopDensity shouldNotContain1 = new CountryWithPopDensity(COUNTRY_1);
        CountryWithPopDensity shouldNotContain2 = new CountryWithPopDensity(COUNTRY_8);

        Mockito.when(abstractCountriesService.getAllEuCountries())
                .thenReturn(TEST_COUNTRIES);

        List<Country> result = mainCountriesService.getLargestPopulation();

        Assertions.assertFalse(
                result.contains(shouldNotContain1)
        );
        Assertions.assertFalse(
                result.contains(shouldNotContain2)
        );
    }

    @Test
    void shouldGetCountriesByLargestArea() {
        CountryWithPopDensity shouldNotContain1 = new CountryWithPopDensity(COUNTRY_1);
        CountryWithPopDensity shouldNotContain2 = new CountryWithPopDensity(COUNTRY_2);

        Mockito.when(abstractCountriesService.getAllEuCountries())
                .thenReturn(TEST_COUNTRIES);

        List<Country> result = mainCountriesService.getLargestArea();

        Assertions.assertFalse(
                result.contains(shouldNotContain1)
        );
        Assertions.assertFalse(
                result.contains(shouldNotContain2)
        );
    }

    @Test
    void shouldGetCountriesByLargestPopulationDensity() {
        CountryWithPopDensity shouldNotContain1 = new CountryWithPopDensity(COUNTRY_11);
        CountryWithPopDensity shouldNotContain2 = new CountryWithPopDensity(COUNTRY_12);

        Mockito.when(abstractCountriesService.getAllEuCountries())
                .thenReturn(TEST_COUNTRIES);

        List<Country> result = mainCountriesService.getLargestPopulationDensity();

        Assertions.assertFalse(
                result.contains(shouldNotContain1)
        );
        Assertions.assertFalse(
                result.contains(shouldNotContain2)
        );
    }
}
