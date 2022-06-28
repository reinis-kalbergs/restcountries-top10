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

    public Boolean filterCountries() {
        return this.population != null && this.area != null;
    }
}
