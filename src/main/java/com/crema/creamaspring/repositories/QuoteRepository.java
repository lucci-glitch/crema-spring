package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {

}
