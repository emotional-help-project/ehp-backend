package com.epam.rd.repository;

import com.epam.rd.model.entity.Test;
import com.epam.rd.model.entity.TestResult;
import com.epam.rd.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends BaseRepository<TestResult> {

    @Query("SELECT tr FROM test_results tr " +
            "INNER JOIN FETCH sessions s ON s = tr.session " +
            "WHERE tr.user = :user AND s.test = :test"
    )
    List<TestResult> getTestResultsByUserAndTest(@Param("user") User user,
                                                 @Param("test") Test test);

    @Query("SELECT tr FROM test_results tr " +
            "INNER JOIN FETCH sessions s ON s = tr.session " +
            "WHERE tr.user = :user"
    )
    List<TestResult> getUserTestResults(@Param("user") User user);
}
