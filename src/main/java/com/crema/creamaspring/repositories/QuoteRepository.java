package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findQuotesByTextContaining(String text);

    List<Quote> findQuotesByCategoryEqualsAndTextContaining(EQouteCategory eQouteCategory, String text);

    @Query(value = "SELECT *" +
            "FROM quote " +
            "WHERE quote.text LIKE %:searchWord1% " +
            "    AND quote.post_id IN (\n" +
            "\n" +
            "        SELECT post.id\n" +
            "        FROM post\n" +
            "                 INNER JOIN\n" +
            "             (\n" +
            "                 SELECT post.forum_thread_id\n" +
            "                 FROM quote\n" +
            "                          INNER JOIN post\n" +
            "                                     ON post.id = quote.post_id\n" +
            "                 WHERE quote.text LIKE %:searchWord1%" +
            "                 GROUP BY post.forum_thread_id\n" +
            "                 ORDER BY COUNT(text) DESC\n" +
            "                 LIMIT 10\n" +
            "             ) AS top_10\n" +
            "             ON top_10.forum_thread_id = post.forum_thread_id\n" +
            "    ) LIMIT 1"
            , nativeQuery = true)
    List<Quote> relevantQuote(@Param("searchWord1") String searchWord1);

    @Query(value = "SELECT *" +
            "FROM quote " +
            "WHERE quote.text LIKE %:searchWord1% AND quote.text LIKE  %:searchWord2% " +
            "   OR quote.text LIKE %:searchWord1% " +
            "    AND quote.post_id IN (\n" +
            "\n" +
            "        SELECT post.id\n" +
            "        FROM post\n" +
            "                 INNER JOIN\n" +
            "             (\n" +
            "                 SELECT post.forum_thread_id\n" +
            "                 FROM quote\n" +
            "                          INNER JOIN post\n" +
            "                                     ON post.id = quote.post_id\n" +
            "                 WHERE quote.text LIKE %:searchWord1%" +
            "                 GROUP BY post.forum_thread_id\n" +
            "                 ORDER BY COUNT(text) DESC\n" +
            "                 LIMIT 10\n" +
            "             ) AS top_10\n" +
            "             ON top_10.forum_thread_id = post.forum_thread_id\n" +
            "    ) LIMIT 1"
            , nativeQuery = true)
    List<Quote> relevantQuote(@Param("searchWord1") String searchWord1, @Param("searchWord2")String searchWord2);

    @Query(value = "SELECT *" +
            "FROM quote " +
            "WHERE quote.text LIKE %:searchWord1% AND quote.text LIKE %:searchWord2% AND quote.text LIKE %:searchWord3%" +
            "   OR quote.text LIKE %:searchWord1% AND quote.text LIKE %:searchWord2% " +
            "   OR quote.text LIKE %:searchWord1% " +
            "    AND quote.post_id IN (\n" +
            "\n" +
            "        SELECT post.id\n" +
            "        FROM post\n" +
            "                 INNER JOIN\n" +
            "             (\n" +
            "                 SELECT post.forum_thread_id\n" +
            "                 FROM quote\n" +
            "                          INNER JOIN post\n" +
            "                                     ON post.id = quote.post_id\n" +
            "                 WHERE quote.text LIKE %:searchWord1%" +
            "                 GROUP BY post.forum_thread_id\n" +
            "                 ORDER BY COUNT(text) DESC\n" +
            "                 LIMIT 10\n" +
            "             ) AS top_10\n" +
            "             ON top_10.forum_thread_id = post.forum_thread_id\n" +
            "    ) LIMIT 2"
            , nativeQuery = true)
    List<Quote> relevantQuote(@Param("searchWord1") String searchWord1, @Param("searchWord2")String searchWord2, @Param("searchWord3")String searchWord3);

    @Query(value = "SELECT *" +
            "FROM quote " +
            "WHERE quote.text LIKE %:searchWord1% AND quote.text LIKE %:searchWord2% AND quote.text LIKE %:searchWord3% AND quote.text LIKE %:searchWord4% " +
            "   OR quote.text LIKE %:searchWord1% AND quote.text LIKE %:searchWord2% AND quote.text LIKE %:searchWord3% " +
            "   OR quote.text LIKE %:searchWord1% AND quote.text LIKE %:searchWord2% " +
            "   OR quote.text LIKE %:searchWord1% " +
            "    AND quote.post_id IN (\n" +
            "\n" +
            "        SELECT post.id\n" +
            "        FROM post\n" +
            "                 INNER JOIN\n" +
            "             (\n" +
            "                 SELECT post.forum_thread_id\n" +
            "                 FROM quote\n" +
            "                          INNER JOIN post\n" +
            "                                     ON post.id = quote.post_id\n" +
            "                 WHERE quote.text LIKE %:searchWord1%" +
            "                 GROUP BY post.forum_thread_id\n" +
            "                 ORDER BY COUNT(text) DESC\n" +
            "                 LIMIT 10\n" +
            "             ) AS top_10\n" +
            "             ON top_10.forum_thread_id = post.forum_thread_id\n" +
            "    ) LIMIT 2"
            , nativeQuery = true)
    List<Quote> relevantQuote(@Param("searchWord1") String searchWord1, @Param("searchWord2")String searchWord2, @Param("searchWord3")String searchWord3, @Param("searchWord4")String searchWord4);

    @Query(value="SELECT * FROM quote WHERE quote.post_id = (SELECT quote.post_id FROM quote WHERE quote.id =:id)", nativeQuery = true)
    List<Quote> findSiblingQuotesById(Integer id);

    @Query(value="SELECT post.forum_thread_id FROM post WHERE post.id = (SELECT quote.post_id FROM quote WHERE quote.id =:id)", nativeQuery = true)
    String findForumThread(Integer id);
}
