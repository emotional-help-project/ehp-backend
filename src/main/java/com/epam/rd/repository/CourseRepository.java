package com.epam.rd.repository;

import com.epam.rd.model.entity.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends BaseRepository<Course>, JpaSpecificationExecutor<Course> {

}
