package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findQuotesByTextContaining(String text);
    List<Quote> findQuotesByCategoryEqualsAndTextContaining(EQouteCategory eQouteCategory, String text);
}
