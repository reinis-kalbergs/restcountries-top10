package com.example.restcountriestop10.service;

import com.example.restcountriestop10.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MainCountriesService {

    private final AllCountriesGetter allCountriesGetter;

    public List<Country> getLargestPopulation() {
        return Arrays.stream(allCountriesGetter.getAllEuCountries())
                .filter(Country::getIndependent)
                .sorted(Comparator.comparing(Country::getPopulation).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Country> getLargestArea() {
        return Arrays.stream(allCountriesGetter.getAllEuCountries())
                .filter(Country::getIndependent)
                .sorted(Comparator.comparing(Country::getArea).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Country> getLargestPopulationDensity() {
        return Arrays.stream(allCountriesGetter.getAllEuCountries())
                .filter(Country::getIndependent)
                .sorted(Comparator.comparing(Country::getPopulationDensity).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
