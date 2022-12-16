package com.epam.rd.repository;

import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdviceRepository extends BaseRepository<Advice> {

    @Query("SELECT a FROM Advice a WHERE a.test = test AND a.scoreFrom <= :userScore and a.scoreTo >= :userScore")
    Optional<Advice> findAdviceByUserScoreAndTestId(@Param("userScore") Long userScore,
                                                    @Param("test") Test test);
}
