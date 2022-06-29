package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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
    @ManyToMany
    @JoinTable(
            name = "country_currencies",
            joinColumns = @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "code"))
    private Set<Currency> currencies;

    public Country(CountryInDatabase countryInDatabase) {
        this.name = countryInDatabase.getName();
        this.capital = countryInDatabase.getCapital();
        this.population = countryInDatabase.getPopulation();
        this.area = countryInDatabase.getArea();
        this.currencies = countryInDatabase.getCurrencies();
    }

    public Boolean filterCountriesWithoutAreaOrPopulation() {
        return this.population != null && this.area != null;
    }
}
