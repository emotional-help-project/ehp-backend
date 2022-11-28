package com.epam.rd.repository;

import com.epam.rd.model.entity.Answer;
import com.epam.rd.model.entity.Session;
import com.epam.rd.model.entity.UserAnswers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswersRepository extends BaseRepository<UserAnswers> {

    @Query("SELECT ua FROM user_answers ua WHERE ua.session = :session")
    List<UserAnswers> findUserAnswersBySession(@Param("session") Session session);

    @Query("SELECT ua FROM user_answers ua WHERE ua.session = :session AND ua.answer IN :answers")
    List<UserAnswers> findUserAnswersBySessionAndAnswers(@Param("session") Session session,
                                                         @Param("answers") List<Answer> answers);

}
