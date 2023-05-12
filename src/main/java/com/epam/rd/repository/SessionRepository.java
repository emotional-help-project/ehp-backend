package com.epam.rd.repository;

import com.epam.rd.model.entity.Session;
import com.epam.rd.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends BaseRepository<Session> {

    @Query("SELECT s FROM sessions s WHERE s.user = :user AND s.isFinished = :isFinished")
    List<Session> findAllByUserAndFinished(@Param("user") User user, @Param("isFinished") Boolean isFinished);

}
