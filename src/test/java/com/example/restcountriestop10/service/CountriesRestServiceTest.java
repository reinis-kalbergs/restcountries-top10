package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountriesRestServiceTest {

    @Mock
    Environment environment;
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    CountriesRestService countriesRestService;

    @Captor
    ArgumentCaptor<String> urlCaptor;

    @Test
    void shouldReceiveCorrectUrl() {
        Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("https://restcountries.com/v2");
        Mockito.when(restTemplate.getForObject(urlCaptor.capture(), Mockito.any()))
                .thenReturn(null);

        countriesRestService.getAllEuCountries();
        List<String> result = urlCaptor.getAllValues();

        Assertions.assertTrue(result.contains("https://restcountries.com/v2/regionalbloc/eu"));
    }

    @Test
    void shouldReturnCountries() {
        Country testCountry1 = new Country("name", "capital", 1L, 1, null);
        Country testCountry2 = new Country("name", "capital", 1L, 1, null);

        Country[] testCountries = {
                testCountry1,
                testCountry2
        };

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any()))
                .thenReturn(testCountries);

        Country[] result = countriesRestService.getAllEuCountries();

        Assertions.assertIterableEquals(Arrays.asList(result), Arrays.asList(testCountries));
    }

}
