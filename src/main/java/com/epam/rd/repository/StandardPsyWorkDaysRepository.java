package com.epam.rd.repository;

import com.epam.rd.model.entity.Psychologist;
import com.epam.rd.model.entity.StandardPsyWorkDays;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardPsyWorkDaysRepository extends BaseRepository<StandardPsyWorkDays> {

    List<StandardPsyWorkDays> findAllByPsychologist(@Param("psychologist")Psychologist psychologist);
}
