package com.example.restcountriestop10.service.getcountriesservice;

import com.example.restcountriestop10.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class CountriesJsonServiceTest {

    @Mock
    Environment environment;
    @Mock
    RestTemplate restTemplate;
    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    CountriesJsonService countriesJsonService;

    @Test
    void shouldReturnCountries() throws IOException {
        Country testCountry1 = new Country("name1", "capital1", 1L, 1, null);
        Country testCountry2 = new Country("name2", "capital2", 1L, 1, null);

        Country[] testCountries = {
                testCountry1,
                testCountry2
        };

        Mockito.when(objectMapper.readValue(Mockito.any(File.class), Mockito.eq(Country[].class)))
                .thenReturn(testCountries);

        Country[] result = countriesJsonService.getAllEuCountries();


        Assertions.assertIterableEquals(Arrays.asList(result), Arrays.asList(testCountries));
    }
}
