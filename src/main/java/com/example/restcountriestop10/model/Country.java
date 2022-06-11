package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String name;
    private Long population;
    private Boolean independent;
    private Integer area;
    private Double populationDensity;

    public double getPopulationDensity() {
        //Spring automatically adds this to the outgoing JSON object
        return (double) this.population / this.area;
    }
}
