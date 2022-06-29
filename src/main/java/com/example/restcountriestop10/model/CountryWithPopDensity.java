package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryWithPopDensity extends Country {
    private Double populationDensity;

    public CountryWithPopDensity(Country country) {
        this.setName(country.getName());
        this.setCapital(country.getCapital());
        this.setPopulation(country.getPopulation());
        this.setArea(country.getArea());
        this.setCurrencies(country.getCurrencies());
        //todo: add currency back
    }

    public Double getPopulationDensity() {
        return (double) getPopulation() / getArea();
    }
}
