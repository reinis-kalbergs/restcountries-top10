package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class CountryInDatabaseV2 {
    @Id
    private String name;
    private String capital;
    private Long population;
    private Integer area;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToMany
    @JoinTable(
            name = "country_currencies",
            joinColumns = @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "code"))
    private Set<Currency> currencies = new HashSet<>();

    public CountryInDatabaseV2(Country country) {
        this.setName(country.getName());
        this.setCapital(country.getCapital());
        this.setPopulation(country.getPopulation());
        this.setArea(country.getArea());
        this.setCurrencies(country.getCurrencies());
    }

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }
}
