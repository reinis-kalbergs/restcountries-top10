package com.example.restcountriestop10.comandlinerunner;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.service.CountriesJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "json")
public class SaveJsonDataOnStart implements CommandLineRunner {

    private final CountriesJsonService countriesJsonService;
    private final String FULL_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\data\\EuCountries.json";
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        List<Country> countriesToSave = new ArrayList<>(Arrays.asList(countriesJsonService.getAllEuCountriesFromRest()));
        removeInvalidData(countriesToSave);
        objectMapper.writeValue(new File(FULL_PATH), countriesToSave);
    }

    private void removeInvalidData(List<Country> countriesToSave) {
        List<Country> countriesToRemove = new ArrayList<>();
        for (Country country : countriesToSave) {
            if (country.getArea() == null) {
                countriesToRemove.add(country);
            }
        }
        countriesToSave.removeAll(countriesToRemove);
    }
}