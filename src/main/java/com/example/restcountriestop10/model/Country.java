package com.example.restcountriestop10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
    @Id
    private String name;
    private String capital;
    private Long population;
    private Integer area;

    /*@JoinColumn(name = "code")
    @ManyToOne
    private Currency[] currencies;*/

    public Boolean filterCountries() {
        return this.population != null && this.area != null;
    }
}
