package com.epam.rd.repository;

import com.epam.rd.model.entity.Appointment;
import com.epam.rd.model.entity.Psychologist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment> {

    @Query("SELECT ap FROM appointments ap " +
            "WHERE ap.psychologist = :psychologist " +
            "AND ap.startDateTime >= :currentDateTime")
    List<Appointment> findBookedAppointmentsByPsychologist(@Param("psychologist") Psychologist psychologist,
                                                           @Param("currentDateTime") LocalDateTime currentDateTime);
}
