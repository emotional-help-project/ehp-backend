package com.epam.rd.repository;

import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends BaseRepository<Link> {

    @Query("SELECT l FROM links l " +
            "INNER JOIN FETCH advice_links al ON l = al.link " +
            "INNER JOIN FETCH Advice a ON al.advice = a " +
            "WHERE al.advice = :advice"
    )
    List<Link> getLinksForAdvice(@Param("advice") Advice advice);
}
