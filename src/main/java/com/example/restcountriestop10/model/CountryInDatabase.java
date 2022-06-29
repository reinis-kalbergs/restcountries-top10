package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Set;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "country_in_database")
public class CountryInDatabase extends Country {

    @ManyToMany
    @JoinTable(
            name = "country_currencies",
            joinColumns = @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "code"))
    private Set<Currency> currencies;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public CountryInDatabase(Country country) {
        this.setName(country.getName());
        this.setCapital(country.getCapital());
        this.setPopulation(country.getPopulation());
        this.setArea(country.getArea());
    }
}
