package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Country {
    @Id
    private String name;
    private String capital;
    private Long population;
    private Integer area;

    /*@ManyToMany
    @JoinColumn(name = "currency_code")
    private List<Currency> currencies;*/

    public Country(CountryInDatabase countryInDatabase) {
        this.name = countryInDatabase.getName();
        this.capital = countryInDatabase.getCapital();
        this.population = countryInDatabase.getPopulation();
        this.area = countryInDatabase.getArea();
    }


    public Boolean filterCountries() {
        return this.population != null && this.area != null;
    }
}
