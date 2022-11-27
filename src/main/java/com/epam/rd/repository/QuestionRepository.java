package com.epam.rd.repository;

import com.epam.rd.model.entity.Question;
import com.epam.rd.model.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends BaseRepository<Question> {

    @Query("SELECT q FROM questions q WHERE q.test = :test")
    Page<Question> findTestQuestionsPaginated(@Param(value = "test") Test test, Pageable pageable);

    Long countByTest(@Param(value = "test") Test test);

    @Query("SELECT q FROM questions q WHERE q.id in :questionIds AND q.multiple_answers = :multipleAnswers")
    List<Question> findByQuestionIdsAndMultipleAnswers(@Param("questionIds") List<Long> questionIds,
                                                       @Param("multipleAnswers") Boolean multipleAnswers);

    List<Question> findAllByTest(@Param(value = "test") Test test);
}
