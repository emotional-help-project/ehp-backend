package com.epam.rd.repository;

import com.epam.rd.model.entity.Psychologist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PsychologistRepository extends BaseRepository<Psychologist> {

    @Query("SELECT psy FROM psychologists psy " +
            "INNER JOIN FETCH occasional_work_time occ ON occ.psychologist = psy " +
            "WHERE occ.date > :currentDateTime")
    List<Psychologist> findAvailablePsychologists(@Param("currentDateTime") LocalDateTime currentDateTime);
}
