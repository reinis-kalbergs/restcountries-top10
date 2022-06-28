package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.service.getcountriesservice.AbstractCountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class CountriesPatternService {

    private final AbstractCountriesService abstractCountriesService;

    public List<Country> getAllCountriesWithPattern(String wildcard) {
        Country[] countries = abstractCountriesService.getAllEuCountries();
        Pattern pattern = createPattern(wildcard);

        return Arrays.stream(countries)
                .filter(country -> {
                    Matcher matcher = pattern.matcher(country.getName());
                    return matcher.matches();
                })
                .toList();
    }

    private Pattern createPattern(String wildcard) {
        String patternString = wildcard.replace("*", ".*");
        return Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
    }
}
