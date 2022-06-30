package com.example.restcountriestop10.controller;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.service.MainCountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/top10")
public class CountriesController {

    private final MainCountriesService mainCountriesService;

    @GetMapping("/eu/population")
    public List<Country> getLargestPopulation() {
        return mainCountriesService.getLargestPopulation();
    }

    @GetMapping("/eu/area")
    public List<Country> getLargestArea() {
        return mainCountriesService.getLargestArea();
    }

    @GetMapping("/eu/population-density")
    public List<Country> getLargestPopulationDensity() {
        return mainCountriesService.getLargestPopulationDensity();
    }
}
