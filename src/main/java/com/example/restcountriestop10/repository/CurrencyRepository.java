package com.example.restcountriestop10.repository;

import com.example.restcountriestop10.model.Currency;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "top10countries", name = "retrieve-from", havingValue = "database-v2")
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
