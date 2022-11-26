package com.epam.rd.repository;

import com.epam.rd.model.entity.Question;
import com.epam.rd.model.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends BaseRepository<Question> {

    @Query("SELECT q FROM questions q WHERE q.test = :test")
    Page<Question> findTestQuestionsPaginated(@Param(value = "test") Test test, Pageable pageable);

}
