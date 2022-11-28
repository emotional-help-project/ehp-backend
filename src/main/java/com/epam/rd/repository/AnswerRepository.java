package com.epam.rd.repository;

import com.epam.rd.model.entity.Answer;
import com.epam.rd.model.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends BaseRepository<Answer> {

    @Query("SELECT a FROM answers a WHERE a.question = :question")
    List<Answer> findByQuestion(@Param("question") Question question);

}
