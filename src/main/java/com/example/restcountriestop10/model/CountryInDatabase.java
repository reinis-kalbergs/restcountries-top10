package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country")
public class CountryInDatabase extends Country {

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public CountryInDatabase(Country country) {
        this.setName(country.getName());
        this.setCapital(country.getCapital());
        this.setPopulation(country.getPopulation());
        this.setArea(country.getArea());
        this.setCurrencies(country.getCurrencies());
    }
}
