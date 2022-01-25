package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findQuotesByTextContaining(String text);

    List<Quote> findQuotesByCategoryEqualsAndTextContaining(EQouteCategory eQouteCategory, String text);

    @Query(value =
            "SELECT quote.text, quote.post_id\n" +
             "FROM quote\n" +
            "WHERE quote.text LIKE :searchWords[1]" +
            "  AND quote.text LIKE  :searchWords[2]" +
            "   OR quote.text LIKE :searchWords[1]" +
            "  AND quote.post_id IN (\n" +
            "\n" +
            "      SELECT post.id\n" +
            "        FROM post\n" +
            "       INNER JOIN\n" +
            "          (" +
            "              SELECT post.forum_thread_id\n" +
            "                FROM quote\n" +
            "                     INNER JOIN post\n" +
            "                           ON post.id = quote.post_id\n" +
            "              WHERE quote.text LIKE :searchWords[0]" +
            "              GROUP BY post.forum_thread_id\n" +
            "              ORDER BY COUNT(text) DESC\n" +
            "              LIMIT 10" +
            "          ) AS top_10\n" +
            "      ON top_10.forum_thread_id = post.forum_thread_id\n" +
            ")", nativeQuery = true)
    List<Quote> relevantQuotes(@Param("searchWords") List<String> searchWords);
}
