package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    List<Quote> findQuotesByTextContaining(String text);

}
