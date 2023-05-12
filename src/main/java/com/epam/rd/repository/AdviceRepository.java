package com.epam.rd.repository;

import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.Session;
import com.epam.rd.model.entity.Test;
import com.epam.rd.model.entity.UserAnswers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdviceRepository extends BaseRepository<Advice> {

    @Query(value = "SELECT a FROM Advice a WHERE a.test = :test AND a.scoreFrom <= :userScore and a.scoreTo >= :userScore")
    Optional<Advice> findAdviceByUserScoreAndTest(@Param("userScore") Long userScore,
                                                  @Param("test") Test test);

    List<Advice> findAllByTest(@Param("test") Test test);

}
