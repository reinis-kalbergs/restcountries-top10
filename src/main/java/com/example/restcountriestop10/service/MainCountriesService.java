package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.model.CountryWithPopDensity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MainCountriesService {

    private final AbstractCountriesService abstractCountriesService;

    public List<Country> getLargestPopulation() {
        return Arrays.stream(abstractCountriesService.getAllEuCountries())
                .filter(Country::filterCountries)
                .map(CountryWithPopDensity::new)
                .sorted(Comparator.comparing(Country::getPopulation).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Country> getLargestArea() {
        return Arrays.stream(abstractCountriesService.getAllEuCountries())
                .filter(Country::filterCountries)
                .map(CountryWithPopDensity::new)
                .sorted(Comparator.comparing(Country::getArea).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Country> getLargestPopulationDensity() {
        return Arrays.stream(abstractCountriesService.getAllEuCountries())
                .filter(Country::filterCountries)
                .map(CountryWithPopDensity::new)
                .sorted(Comparator.comparing(CountryWithPopDensity::getPopulationDensity).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
