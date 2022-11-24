package com.epam.rd.repository;

import com.epam.rd.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends BaseRepository<Answer> {
}