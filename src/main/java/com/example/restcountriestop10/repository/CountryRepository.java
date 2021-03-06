package com.example.restcountriestop10.repository;

import com.example.restcountriestop10.model.CountryInDatabase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "database")
public interface CountryRepository extends JpaRepository<CountryInDatabase, String> {

    boolean existsByLastUpdatedBefore(LocalDateTime givenDateTime);

}
