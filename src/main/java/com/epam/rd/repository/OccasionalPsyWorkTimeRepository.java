package com.epam.rd.repository;

import com.epam.rd.model.entity.OccasionalPsyWorkTime;
import com.epam.rd.model.entity.Psychologist;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccasionalPsyWorkTimeRepository extends BaseRepository<OccasionalPsyWorkTime> {

    List<OccasionalPsyWorkTime> findAllByPsychologist(@Param("psychologist") Psychologist psychologist);

}
