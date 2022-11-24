package com.epam.rd.repository;

import com.epam.rd.model.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends BaseRepository<TestResult> {
}
