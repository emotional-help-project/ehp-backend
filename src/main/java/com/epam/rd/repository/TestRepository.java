package com.epam.rd.repository;

import com.epam.rd.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends BaseRepository<Test> {
}
