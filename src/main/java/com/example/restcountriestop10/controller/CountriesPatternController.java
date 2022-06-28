package com.example.restcountriestop10.controller;

import com.example.restcountriestop10.model.Country;
import com.example.restcountriestop10.service.CountriesPatternService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CountriesPatternController {

    private final CountriesPatternService countriesPatternService;

    @GetMapping("/countries-match")
    List<Country> getAllCountriesWithPattern(@RequestParam String pattern) {
        return countriesPatternService.getAllCountriesWithPattern(pattern);
    }
}
