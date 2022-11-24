package com.epam.rd.repository;

import com.epam.rd.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends BaseRepository<Question> {
}
