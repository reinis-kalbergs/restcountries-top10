package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String name;
    private String capital;
    private Long population;
    private Integer area;
    private Currency[] currencies;
    private Double populationDensity;

    public Double getPopulationDensity() {
        if (this.population == null || this.area == null) {
            return null;
        }
        return (double) this.population / this.area;
    }
}
