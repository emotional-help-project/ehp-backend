package com.epam.rd.repository;

import com.epam.rd.model.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends BaseRepository<Test> {

    @Query("SELECT t FROM tests t")
    Page<Test> findAllTestsPaginated(Pageable pageable);

}
